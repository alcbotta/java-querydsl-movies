#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    
    CREATE DATABASE dev_database;
    GRANT ALL PRIVILEGES ON DATABASE dev_database TO postgres;

    CREATE DATABASE test_database;
    GRANT ALL PRIVILEGES ON DATABASE test_database TO postgres;
EOSQL