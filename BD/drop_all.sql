DROP TRIGGER IF EXISTS transaction_accounts_check ON Transactions;
DROP FUNCTION IF EXISTS transaction_accounts_check;
DROP TABLE IF EXISTS Transactions;

DROP TABLE IF EXISTS Offices;

DROP TRIGGER IF EXISTS account_control_sum_check ON Accounts;
DROP FUNCTION IF EXISTS account_control_sum_check;
DROP TABLE IF EXISTS Accounts;

DROP TYPE IF EXISTS account_status; 
DROP TABLE IF EXISTS Account_types;

DROP TRIGGER IF EXISTS lclients_trigger ON Legal_clients;
DROP TRIGGER IF EXISTS pclients_trigger ON Physical_clients;
DROP FUNCTION IF EXISTS lclients_trigger;
DROP FUNCTION IF EXISTS pclients_trigger;
DROP TABLE IF EXISTS Legal_clients;
DROP TABLE IF EXISTS Physical_clients;
DROP TABLE IF EXISTS Clients;
DROP TYPE IF EXISTS client_type;