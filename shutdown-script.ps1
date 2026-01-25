# Script PowerShell per il dump dei dati su Windows
# Versione migliorata per gestire correttamente UTF-8 senza BOM e caratteri accentati

$SQL_FILE = "src\main\resources\data.sql"
$PROPS_FILE = "src\main\resources\application.properties"

# Rileva il profilo attivo
$ActiveProfile = "local" # Default
if (Test-Path $PROPS_FILE) {
    Try {
        $lines = Get-Content $PROPS_FILE -ErrorAction Stop
        foreach ($line in $lines) {
            if ($line -match "^\s*spring\.profiles\.active\s*=\s*(.*)") {
                $ActiveProfile = $matches[1].Trim()
                break
            }
        }
    } Catch {
        Write-Host "Impossibile leggere application.properties"
    }
}

Write-Host "Rilevato profilo attivo: $ActiveProfile"

if ($ActiveProfile -ne "local") {
    Write-Host "⚠️  AGGIORNAMENTO DATA.SQL DISABILITATO"
    Write-Host "Il profilo attivo è '$ActiveProfile'. Lo script di shutdown aggiorna 'data.sql' solo in profilo 'local'."
    exit
}

$DB_NAME = "SiwRecipes"
$DB_HOST = "localhost"
$DB_PORT = "5432"
$DB_USER = "postgres"
$DB_PASSWORD = "postgres"

# Configura la console per usare UTF-8
[Console]::OutputEncoding = [System.Text.Encoding]::UTF8
$env:PGCLIENTENCODING = "UTF8"
$env:PGPASSWORD = $DB_PASSWORD

# Prepara l'encoding UTF-8 senza BOM
$utf8NoBom = New-Object System.Text.UTF8Encoding $false

# Inizializza il contenuto del file
$content = New-Object System.Collections.Generic.List[string]
$dateStr = Get-Date -Format "ddd dd MMM yyyy HH:mm:ss"
$content.Add("/* Dati iniziali salvati automaticamente su Edoardo-Pc $dateStr CET */")
$content.Add("")

# Funzione helper per dumpare una tabella
function Get-TableDump {
    param (
        [string]$TableName
    )
    $localLines = New-Object System.Collections.Generic.List[string]
    $localLines.Add("-- Dump dati per la tabella: $TableName")
    
    # Esegue pg_dump
    # Nota: Usiamo cmd /c per assicurarci che l'encoding della pipe sia gestito correttamente
    $dumpOutput = cmd /c "pg_dump.exe --username $DB_USER --dbname $DB_NAME --host $DB_HOST --port $DB_PORT --table public.$TableName --data-only --column-inserts"

    if ($dumpOutput) {
        foreach ($line in $dumpOutput) {
            # Filtra le righe indesiderate
            if ($line -notmatch "^--" -and 
                $line -notmatch "^SET" -and 
                $line -notmatch "^SELECT pg_catalog" -and 
                $line -notmatch "^\\" -and 
                $line -notmatch "^$") {
                $localLines.Add($line)
            }
        }
    }
    
    $localLines.Add("")
    return $localLines
}

# Raccogli i dati
$content.AddRange([string[]](Get-TableDump "users"))
$content.AddRange([string[]](Get-TableDump "credentials"))
$content.AddRange([string[]](Get-TableDump "recipe"))
$content.AddRange([string[]](Get-TableDump "ingredient"))
$content.AddRange([string[]](Get-TableDump "review"))

# Reset delle sequenze
$content.Add("")
$content.Add("-- Reset delle sequenze per la generazione degli ID")

$sequences = @(
    @{Seq="credentials_seq"; Table="credentials"},
    @{Seq="users_seq"; Table="users"},
    @{Seq="recipe_seq"; Table="recipe"},
    @{Seq="ingredient_seq"; Table="ingredient"},
    @{Seq="review_seq"; Table="review"}
)

foreach ($item in $sequences) {
    $cmd = "SELECT setval('$($item.Seq)', (SELECT MAX(id) FROM public.$($item.Table)));"
    $content.Add($cmd)
}

# Scrivi tutto su file in un colpo solo usando UTF-8 senza BOM
[System.IO.File]::WriteAllLines($SQL_FILE, $content, $utf8NoBom)

Write-Host "Dump completato in $SQL_FILE (UTF-8 No BOM)"
