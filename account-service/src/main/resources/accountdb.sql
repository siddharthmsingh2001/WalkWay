DROP DATABASE IF EXISTS accountdb;
CREATE DATABASE IF NOT EXISTS accountdb;
USE accountdb;

CREATE TABLE account (
    account_id BINARY(16) PRIMARY KEY, -- same as Keycloak UUID
    account_email VARCHAR(255) NOT NULL UNIQUE, -- also stored in Keycloak, but kept here for fast joins
    account_role ENUM('ADMIN', 'BUYER', 'SELLER'),
    account_phone VARCHAR(20) NULL,
    account_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    account_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE account_profile (
    profile_id BINARY(16) PRIMARY KEY,
    account_id BINARY(16) NOT NULL UNIQUE,
    profile_first_name VARCHAR(100),
    profile_last_name VARCHAR(100),
    profile_dob DATE,
    profile_gender ENUM('MALE','FEMALE','OTHER'),
    profile_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    profile_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT account_profile_fk FOREIGN KEY (account_id) REFERENCES account(account_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE account_address (
    address_id BINARY(16) PRIMARY KEY,
    account_id BINARY(16) NOT NULL,
    address_line1 VARCHAR(255) NOT NULL,
    address_line2 VARCHAR(255),
    address_city VARCHAR(100) NOT NULL,
    address_state VARCHAR(100) NOT NULL,
    address_zipcode VARCHAR(20) NOT NULL,
    address_country VARCHAR(50) NOT NULL,
    address_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    address_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT account_address_fk FOREIGN KEY (account_id) REFERENCES account(account_id) ON UPDATE CASCADE ON DELETE CASCADE
);

SELECT * FROM account;
SELECT * FROM account_profile;
SELECT * FROM account_address;


-- CREATE TABLE account_credential (
--     credential_id BINARY(16) PRIMARY KEY,
--     account_id BINARY(16) NOT NULL UNIQUE, -- UNIQUE if we want only want one login way either LOCAL or any ELSE remove UNIQUE
--     credential_password VARCHAR(60) NOT NULL,
--     credential_provider ENUM('LOCAL','GOOGLE') DEFAULT 'LOCAL',
--     credential_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     credential_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--     CONSTRAINT account_credential_fk FOREIGN KEY(account_id) REFERENCES account(account_id) ON UPDATE CASCADE ON DELETE CASCADE
-- );

-- CREATE TABLE account (
--     account_id BINARY(16) PRIMARY KEY, -- CHAR(36)
--     account_role ENUM('BUYER','SELLER','ADMIN') NOT NULL,
--     account_email VARCHAR(255) UNIQUE NOT NULL,
--     account_phone VARCHAR(20) NOT NULL,
--     account_locked TINYINT(1) NOT NULL,
--     account_enabled TINYINT(1) NOT NULL,
--     account_created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--     account_updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
-- );