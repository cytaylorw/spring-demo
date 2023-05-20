/*
*  Initialize demo database for MSSQL
*    - Create demo DB
*    - Create demo schema
*
*/

USE master;
GO

-- Create demo DB
IF (db_id('demo') is null)
	CREATE DATABASE demo;
GO
USE demo;

-- Create demo schema
IF (SCHEMA_ID('demo') is null)
	EXEC ('CREATE SCHEMA demo');
GO
