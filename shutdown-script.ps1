# Script PowerShell per il dump dei dati su Windows

$SQL_FILE = "src\main\resources\data.sql"
$DB_NAME = "SiwRecipes"
$DB_HOST = "localhost"
$DB_PORT = "5432"
$DB_USER = "postgres"

# Sovrascrive il file con l'intestazione
Set-Content -Path $SQL_FILE -Value "/* Dati iniziali salvati automaticamente il $(Get-Date) */"
Add-Content -Path $SQL_FILE -Value ""

# Funzione helper per dumpare una tabella
function Dump-Table {
    param (
        [string]$TableName
    )
    Add-Content -Path $SQL_FILE -Value "-- Dump dati per la tabella: $TableName"
    
    # Esegue pg_dump e filtra solo le righe che iniziano con INSERT
    pg_dump.exe --username $DB_USER --dbname $DB_NAME --host $DB_HOST --port $DB_PORT --table "public.$TableName" --data-only --column-inserts | Select-String -Pattern "^INSERT" | Add-Content -Path $SQL_FILE
    
    Add-Content -Path $SQL_FILE -Value ""
}

# Dump delle tabelle (Ordine: Users -> Credentials -> Recipe -> Ingredient, Review)
Dump-Table "users"
Dump-Table "credentials"
Dump-Table "recipe"
Dump-Table "ingredient"
Dump-Table "review"

# Reset delle sequenze
Add-Content -Path $SQL_FILE -Value ""
Add-Content -Path $SQL_FILE -Value "-- Reset delle sequenze per la generazione degli ID"

$sequences = @(
    @{Seq="credentials_seq"; Table="credentials"},
    @{Seq="users_seq"; Table="users"},
    @{Seq="recipe_seq"; Table="recipe"},
    @{Seq="ingredient_seq"; Table="ingredient"},
    @{Seq="review_seq"; Table="review"}
)

foreach ($item in $sequences) {
    $cmd = "SELECT setval('$($item.Seq)', (SELECT MAX(id) FROM public.$($item.Table)));"
    Add-Content -Path $SQL_FILE -Value $cmd
}

Write-Host "Dump completato in $SQL_FILE"
