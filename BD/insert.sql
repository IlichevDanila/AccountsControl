INSERT INTO Clients(type, phone, address) VALUES('physical', '89734807307', 'Москва, улица Ельцина, 62');
INSERT INTO Physical_clients(full_name, passport, tin) VALUES('Иванов Мария', '4505196417', '933687326432');

INSERT INTO Clients(type, phone, address) VALUES('physical', '79023704936', 'Владивосток, улица Карла-Маркса, 120');
INSERT INTO Physical_clients(full_name, passport, tin) VALUES('Кравченко Мария', '4510357406', '109107136769');

INSERT INTO Clients(type, phone, address) VALUES('physical', '89333109448', 'Москва, улица Строителей, 33');
INSERT INTO Physical_clients(full_name, passport, tin) VALUES('Иванов Виктор', '4515752796', '883823080904');

INSERT INTO Clients(type, phone, address) VALUES('physical', '79495115354', 'Саратовv, улица Ельцина, 30');
INSERT INTO Physical_clients(full_name, passport, tin) VALUES('Савченко Мария', '4504549236', '926921070026');

INSERT INTO Clients(type, phone, address) VALUES('physical', '89971503763', 'Санкт Петербург, улица Ленина, 83');
INSERT INTO Physical_clients(full_name, passport, tin) VALUES('Савченко Мария', '4507395042', '791219627649');

INSERT INTO Clients(type, phone, address) VALUES('physical', '89946143628', 'Санкт Петербург, улица Строителей, 84');
INSERT INTO Physical_clients(full_name, passport, tin) VALUES('Куразов Павел', '4518565744', '578218496773');

INSERT INTO Clients(type, phone, address) VALUES('physical', '79670111065', 'Новосибирск, улица Ленина, 60');
INSERT INTO Physical_clients(full_name, passport, tin) VALUES('Савченко Мария', '4512511151', '936888033418');

INSERT INTO Clients(type, phone, address) VALUES('physical', '79686141883', 'Владивосток, улица Карла-Маркса, 96');
INSERT INTO Physical_clients(full_name, passport, tin) VALUES('Иванов Павел', '4516391994', '201090330393');

INSERT INTO Clients(type, phone, address) VALUES('physical', '89312698024', 'Владивосток, Ленинский пр-кт, 31');
INSERT INTO Physical_clients(full_name, passport, tin) VALUES('Савченко Виктор', '4504269047', '994851603875');

INSERT INTO Clients(type, phone, address) VALUES('physical', '89814700214', 'Санкт Петербург, Ленинский пр-кт, 75');
INSERT INTO Physical_clients(full_name, passport, tin) VALUES('Савченко Владимир', '4504332998', '536965476846');


INSERT INTO Offices(phone, address) VALUES('89509579790', 'Владивосток, Ленинский пр-кт, 13');
INSERT INTO Offices(phone, address) VALUES('89235581060', 'Москва, улица Строителей, 25');
INSERT INTO Offices(phone, address) VALUES('89786192552', 'Саратовv, улица Ленина, 24');
INSERT INTO Offices(phone, address) VALUES('89998188494', 'Владивосток, улица Ельцина, 125');
INSERT INTO Offices(phone, address) VALUES('89046156200', 'Владивосток, улица Ельцина, 112');
INSERT INTO Offices(phone, address) VALUES('79244192248', 'Москва, улица Строителей, 60');
INSERT INTO Offices(phone, address) VALUES('79367855409', 'Новосибирск, улица Ельцина, 32');
INSERT INTO Offices(phone, address) VALUES('89958321613', 'Саратовv, Ленинский пр-кт, 38');
INSERT INTO Offices(phone, address) VALUES('89491077853', 'Санкт Петербург, улица Ельцина, 7');
INSERT INTO Offices(phone, address) VALUES('79971466638', 'Новосибирск, улица Карла-Маркса, 10');

INSERT INTO Account_types(name, profitability_percent, profitability_fixed, debiting, accrual, period) VALUES('Копи и сберегай', 0.5, NULL, TRUE, TRUE, 'Monthly');
INSERT INTO Account_types(name, profitability_percent, profitability_fixed, debiting, accrual, period) VALUES('Быстрый старт', -1.5, NULL, TRUE, TRUE, 'Monthly');
INSERT INTO Account_types(name, profitability_percent, profitability_fixed, debiting, accrual, period) VALUES('Микрозайм', -3.0, NULL, TRUE, TRUE, 'Weekly');
INSERT INTO Account_types(name, profitability_percent, profitability_fixed, debiting, accrual, period) VALUES('Карточный счет', NULL, 0.0, TRUE, TRUE, 'Annually');

INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40502961300039431303', 1, 1, 'Frozen', -111942.11, '2011-04-02 16:27:37', '40809978156646528507', NULL);
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40501978500035688960', 1, 3, 'Opened', 173610.4, '2006-09-09 13:23:24', '40808959420136160134', '40801959584027828774');
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40501961800041578470', 2, 3, 'Frozen', -106333.16, '2007-07-21 13:35:40', '40804959562234010705', '40804840918689684630');
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40701959800049107299', 2, 2, 'Opened', 19685.17, '2020-08-27 11:33:03', '40503643767357701646', '40502959826343042356');
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40603959100065238018', 3, 2, 'Frozen', 619116.9, '2016-09-04 16:40:09', '40701978862414977316', '40502959282606831039');
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40702978500090015775', 3, 3, 'Frozen', 111332.11, '2012-09-22 09:45:00', '40502840484983877445', '40806961793591299419');
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40804959200027413046', 3, 3, 'Opened', 208929.9, '2012-08-12 19:32:44', '40817643358773794138', '40603840575120586381');
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40806840800024032144', 4, 3, 'Opened', 269273.0, '2019-03-05 18:12:43', '40601643064535077852', '40807959337541616781');
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40601840800039198896', 5, 4, 'Opened', -20511.10, '2016-03-27 12:45:00', NULL, NULL);
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40702959400037538787', 6, 4, 'Opened', 231996.10, '2009-05-25 10:52:48', NULL, NULL);
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40703978000012244900', 6, 2, 'Frozen', 311306.2, '2014-04-05 15:33:09', '40806959034410970234', '40804643557821025767');
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40807840900009461040', 6, 1, 'Opened', 235248.11, '2007-07-20 15:33:11', '40702840552651702545', NULL);
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40603959900074670016', 7, 1, 'Frozen', -111938.14, '2010-05-20 17:00:20', '40806978049566980542', NULL);
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40804978200014740363', 7, 1, 'Opened', -449393.6, '2018-09-07 11:07:29', '40602840913912029790', NULL);
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40503961900055362606', 7, 4, 'Opened', -240699.6, '2002-06-08 12:42:43', NULL, NULL);
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40602959500050932014', 8, 4, 'Frozen', 186697.6, '2016-03-20 17:14:25', NULL, NULL);
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40809840900071695046', 9, 1, 'Opened', 25157.6, '2001-07-16 14:24:31', '40702961699906770035', NULL);
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40817840900049859495', 9, 1, 'Opened', 520355.16, '2015-12-11 10:41:16', '40804961089859741894', NULL);
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40503961500068383496', 10, 3, 'Frozen', -118638.3, '2007-01-05 18:37:37', '40501959021623417546', '40804961118708917763');
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40801840800035749599', 10, 3, 'Opened', 286689.8, '2020-04-08 10:11:45', '40801959693296762662', '40703643823745577906');
INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('40702840800036022961', 10, 4, 'Opened', 347691.7, '2011-07-16 11:35:14', NULL, NULL);

INSERT INTO Transactions(debit_account_id, credit_account_id, tran_time, amount) VALUES('40802840914519166017', '40502961300039431303', '2010-04-26 16:50:09', 3759);
INSERT INTO Transactions(debit_account_id, credit_account_id, tran_time, amount) VALUES('40805978387925134582', '40702978500090015775', '2020-07-09 17:23:04', 5362);
INSERT INTO Transactions(debit_account_id, credit_account_id, tran_time, amount) VALUES('40809959139105023084', '40703978000012244900', '2003-05-17 18:46:00', 5374);
INSERT INTO Transactions(debit_account_id, credit_account_id, tran_time, amount) VALUES('40805961318886905881', '40702959400037538787', '2012-06-20 17:13:21', 5324);
INSERT INTO Transactions(debit_account_id, credit_account_id, tran_time, amount) VALUES('40801840911365250744', '40804978200014740363', '2003-11-01 11:44:21', 3786);
INSERT INTO Transactions(debit_account_id, credit_account_id, tran_time, amount) VALUES('40602978686172418256', '40502961300039431303', '2019-07-12 09:23:23', 4100);
INSERT INTO Transactions(debit_account_id, credit_account_id, tran_time, amount) VALUES('40503959871611941515', '40503961900055362606', '2005-05-15 20:21:02', 5550);
INSERT INTO Transactions(debit_account_id, credit_account_id, tran_time, amount) VALUES('40805978662454250584', '40807840900009461040', '2005-05-12 17:44:30', 3864);
INSERT INTO Transactions(debit_account_id, credit_account_id, tran_time, amount) VALUES('40603961234806799696', '40817840900049859495', '2003-10-02 20:06:58', 5632);
INSERT INTO Transactions(debit_account_id, credit_account_id, tran_time, amount) VALUES('40603643430771200125', '40602959500050932014', '2006-11-14 13:06:58', 7834);

