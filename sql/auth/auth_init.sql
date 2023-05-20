/*
*  Initialize auth database for PostgreSQL
*    - Install UUID extension
*    - Create auth DB
*    - Create auth schema
*
*/

-- Install UUID extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create auth DB
SELECT 'CREATE DATABASE auth'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'auth')\gexec
\connect auth;

-- Create auth schema
CREATE SCHEMA IF NOT EXISTS auth;

