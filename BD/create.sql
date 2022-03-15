CREATE TYPE client_type as ENUM ('physical', 'legal');

CREATE TABLE Clients(
	id SERIAL PRIMARY KEY,
	type client_type NOT NULL,
	phone char(11) NOT NULL CHECK(phone SIMILAR TO '[123456789][0123456789]{10}'),
	address text NOT NULL CHECK(address != '')
);

CREATE TABLE Physical_clients(
	id serial REFERENCES Clients(id),
	full_name text NOT NULL CHECK(full_name != ''),
	passport char(10) NOT NULL CHECK(passport SIMILAR TO '[0123456789]{10}'),
	tin char(12) NOT NULL CHECK(tin SIMILAR TO '[0123456789]{12}')
);

CREATE TABLE Legal_clients(
	id serial REFERENCES Clients(id),
	name text NOT NULL CHECK(name != ''),
	form text NOT NULL CHECK(form != ''),
	tin char(10) NOT NULL CHECK(tin SIMILAR TO '[0123456789]{10}')
);


CREATE FUNCTION pclients_trigger() RETURNS trigger AS $pclients_trigger$
    BEGIN
        -- Проверить, что указаны имя сотрудника и зарплата
        IF NEW.id IN (SELECT id FROM Legal_clients) THEN
            RAISE EXCEPTION 'Physical client is in the legal clients list';
        END IF;
        
        RETURN NEW;
    END;
$pclients_trigger$ LANGUAGE plpgsql;

CREATE TRIGGER pclients_trigger BEFORE INSERT OR UPDATE ON Physical_clients
    FOR EACH ROW EXECUTE PROCEDURE pclients_trigger();

CREATE FUNCTION lclients_trigger() RETURNS trigger AS $lclients_trigger$
    BEGIN
        -- Проверить, что указаны имя сотрудника и зарплата
        IF NEW.id IN (SELECT id FROM Physical_clients) THEN
            RAISE EXCEPTION 'Legal client is in the physical clients list';
        END IF;
        
        RETURN NEW;
    END;
$lclients_trigger$ LANGUAGE plpgsql;

CREATE TRIGGER lclients_trigger BEFORE INSERT OR UPDATE ON Legal_clients
    FOR EACH ROW EXECUTE PROCEDURE lclients_trigger();


CREATE TYPE profitability_period as ENUM ('Daily', 'Weekly', 'Monthly', 'Quarterly', 'Annually');

CREATE TABLE Account_types(
	id SERIAL PRIMARY KEY,
	name text NOT NULL CHECK(name != ''),
	profitability_percent numeric(4, 2),
	profitability_fixed numeric(17, 2),
	CONSTRAINT profitability_check CHECK((profitability_percent IS NULL AND profitability_fixed IS NOT NULL) OR (profitability_percent IS NOT NULL AND profitability_fixed IS NULL)),
	debiting boolean NOT NULL,
	accrual boolean NOT NULL,
	period profitability_period NOT NULL
);

CREATE TYPE account_status as ENUM ('Opened', 'Closed', 'Frozen');

CREATE TABLE Accounts(
	id char(20) PRIMARY KEY CHECK(id SIMILAR TO '[0123456789]{20}'),
	client_id serial REFERENCES Clients(id),
	type serial REFERENCES Account_types(id),
	status account_status NOT NULL,
	balance numeric(17, 2) NOT NULL,
	creating_time timestamp NOT NULL,
	response_account char(20) NULL CHECK(id SIMILAR TO '[0123456789]{20}'),
	loan_account char(20) NULL CHECK(id SIMILAR TO '[0123456789]{20}')
);

CREATE FUNCTION account_control_sum_check() RETURNS trigger AS $account_control_sum_check$
DECLARE
	s integer := 0;
	ch integer;
	i integer;
    BEGIN
		FOR i in 1..20 LOOP
			ch := ascii(substring(new.id from i for 1)) - ascii('0');
			CASE (i % 3)
				WHEN 1 THEN
					s := s + (7 * ch);
				WHEN 2 THEN
					s := s + ch;
				ELSE
					s := s + (3 * ch);
			END CASE;
		END LOOP;
		s := s % 10;
		
        IF (s != 0) THEN
            RAISE EXCEPTION 'Checksum of account % is invalid: %', new.id, s;
        END IF;
        
        RETURN NEW;
    END;
$account_control_sum_check$ LANGUAGE plpgsql;

CREATE TRIGGER account_control_sum_check BEFORE INSERT OR UPDATE ON Accounts
    FOR EACH ROW EXECUTE PROCEDURE account_control_sum_check();

CREATE TABLE Offices(
	id SERIAL PRIMARY KEY,
	phone char(11) NOT NULL CHECK(phone SIMILAR TO '[123456789][0123456789]{10}'),
	address text NOT NULL CHECK(address != '')
);

CREATE TABLE Transactions(
	id SERIAL PRIMARY KEY,
	debit_account_id char(20) NOT NULL CHECK(debit_account_id SIMILAR TO '[0123456789]{20}'),
	credit_account_id char(20) NOT NULL CHECK(credit_account_id SIMILAR TO '[0123456789]{20}'),
	tran_time timestamp NOT NULL,
	amount numeric(17, 2) NOT NULL
);

CREATE FUNCTION transaction_accounts_check() RETURNS trigger AS $transaction_accounts_check$
    BEGIN
        IF (NEW.debit_account_id NOT IN (SELECT id FROM Accounts)) AND (NEW.credit_account_id NOT IN (SELECT id FROM Accounts)) THEN
            RAISE EXCEPTION 'Debit or credit account shold be in Accounts table';
        END IF;
        
        RETURN NEW;
    END;
$transaction_accounts_check$ LANGUAGE plpgsql;

CREATE TRIGGER transaction_accounts_check BEFORE INSERT OR UPDATE ON Transactions
    FOR EACH ROW EXECUTE PROCEDURE transaction_accounts_check();