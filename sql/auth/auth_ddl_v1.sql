/*
*  Initial version of Auth database schema for PostgreSQL
*    - Create table auth.users
*    - Comment auth.users
*    - Create table auth.user_passwords
*    - Comment auth.user_passwords
*
*/

-- Create table auth.users
CREATE TABLE IF NOT EXISTS auth.users (
  user_id uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
  user_username varchar(50) NOT NULL UNIQUE,
  user_first_name text,
  user_last_name text,
  user_email varchar(50) NOT NULL UNIQUE,
  created_by varchar(50) NOT NULL,
  created_at timestamp NOT NULL,
  updated_by varchar(50),
  updated_at timestamp
);

-- Comment auth.users
comment on table auth.users is 'User account information';

comment on column auth.users.user_id is 'The user ID';
comment on column auth.users.user_username is 'The user username';
comment on column auth.users.user_first_name is 'The user first name';
comment on column auth.users.user_last_name is 'The user username';
comment on column auth.users.user_email is 'The user email';
comment on column auth.users.created_by is 'The record created by this user';
comment on column auth.users.created_at is 'The record creation date';
comment on column auth.users.updated_by is 'The record last updated by this user';
comment on column auth.users.updated_at is 'The record last updated date';



-- Create table auth.user_passwords
CREATE TABLE IF NOT EXISTS auth.user_passwords (
  user_id uuid NOT NULL REFERENCES auth.users (user_id),
  user_password varchar(50) NOT NULL,
  created_by varchar(50) NOT NULL,
  created_at timestamp NOT NULL,
  PRIMARY KEY (user_id, user_password)
);

-- Comment auth.user_passwords
comment on table auth.user_passwords is 'User password information';

comment on column auth.user_passwords.user_id is 'The user ID';
comment on column auth.user_passwords.user_password is 'The user password';
comment on column auth.user_passwords.created_by is 'The record created by this user';
comment on column auth.user_passwords.created_at is 'The record creation date';