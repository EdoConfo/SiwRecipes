#!/bin/bash

# Definisce il percorso del file SQL
SQL_FILE="src/main/resources/data.sql"
PROPS_FILE="src/main/resources/application.properties"

# Rileva il profilo attivo
ACTIVE_PROFILE=$(grep "spring.profiles.active" "$PROPS_FILE" | cut -d'=' -f2 | xargs)
echo "Rilevato profilo attivo: $ACTIVE_PROFILE"

# Configurazione connessione database basata sul profilo
if [ "$ACTIVE_PROFILE" == "supabase" ]; then
    echo "Configurazione script per Supabase..."
    DB_NAME="postgres"
    DB_HOST="aws-1-eu-north-1.pooler.supabase.com"
    DB_PORT="5432"
    DB_USER="postgres.ymhfmdbkpbffrinammzy"
    export PGPASSWORD="ModelloDiDominio"
else
    # Default to local settings
    echo "Configurazione script per Localhost..."
    DB_NAME="SiwRecipes"
    DB_HOST="localhost"
    DB_PORT="5432"
    DB_USER="postgres"
    export PGPASSWORD="postgres"
fi

# Sovrascrive il file con l'intestazione
echo "/* Dati iniziali salvati automaticamente su Edoardo's MacBook Pro $(date '+%a %d %b %Y %H:%M:%S %Z') da profilo $ACTIVE_PROFILE */" > "$SQL_FILE"
echo "" >> "$SQL_FILE"

# Funzione per dumpare una tabella
dump_table() {
    local TABLE_NAME=$1
    echo "-- Dump dati per la tabella: $TABLE_NAME" >> "$SQL_FILE"
    # pg_dump flags:
    # --data-only: solo i dati, niente schema (create table)
    # --column-inserts: usa INSERT INTO table (col1, col2) VALUES ... (più sicuro)
    # Filtriamo via i commenti e i comandi SET/SELECT di pg_dump, mantenendo solo gli INSERT
    pg_dump --username "$DB_USER" --dbname "$DB_NAME" --host "$DB_HOST" --port "$DB_PORT" --table "public.$TABLE_NAME" --data-only --column-inserts | sed '/^SET/d' | sed '/^SELECT pg_catalog/d' | sed '/^--/d' | sed '/^\\/d' | sed '/^$/d' >> "$SQL_FILE"
    echo "" >> "$SQL_FILE"
}

# Dump delle tabelle nell'ordine corretto per rispettare le foreign keys
# Ordine corretto: Users (indipendente) -> Credentials (dipende da User) -> Recipe (dipende da User) -> Ingredient/Review (dipendono da Recipe)

dump_table "users"
dump_table "credentials"
dump_table "recipe"
dump_table "ingredient"
dump_table "review"

# Reset delle sequenze
# Hibernate 6 con GenerationType.AUTO su Postgres usa solitamente sequenze chiamate nomeentita_seq
# Aggiungiamo i comandi per resettare le sequenze al valore massimo corrente + 1

reset_sequence() {
    local SEQ_NAME=$1
    local TABLE_NAME=$2
    echo "-- Aggiorna la sequenza $SEQ_NAME" >> "$SQL_FILE"
    echo "SELECT setval('$SEQ_NAME', (SELECT MAX(id) FROM public.$TABLE_NAME));" >> "$SQL_FILE"
}

{
  echo "-- Reset delle sequenze per la generazione degli ID"
  
  # Nota: I nomi delle sequenze dipendono dalla strategia di naming di Hibernate.
  # Assumiamo lo standard <table_name>_seq o <entity_name>_seq.
  # Se fallisce, bisognerà verificare i nomi esatti nel DB.
  
  echo "SELECT setval('credentials_seq', (SELECT MAX(id) FROM public.credentials));"
  echo "SELECT setval('users_seq', (SELECT MAX(id) FROM public.users));"
  echo "SELECT setval('recipe_seq', (SELECT MAX(id) FROM public.recipe));"
  echo "SELECT setval('ingredient_seq', (SELECT MAX(id) FROM public.ingredient));"
  echo "SELECT setval('review_seq', (SELECT MAX(id) FROM public.review));"
  
} >> "$SQL_FILE"

echo "Dump completato in $SQL_FILE"
