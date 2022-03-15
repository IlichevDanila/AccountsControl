#include <iostream>
#include <fstream>
#include <string>
#include <random>
#include <cstring>
#include <algorithm>
#include <vector>

std::mt19937 gen(72);
std::uniform_int_distribution<int> uniform_dist(0, 1000000000);

std::string expand(int v, int n)
{
	int c = v;
	int t = 0;

	while(c > 0)
	{
		++t;
		c /= 10;
	}
	t = t > 0 ? t : 1;

	std::string res;
	for(; t < n; ++t)
	{
		res += "0";
	}
	res += std::to_string(v);

	return res;
}

std::string divide(int v, int n)
{
	int copy = v;
	int l = n;
	int r = 0;

	for(; n > 0; --n)
	{
		r += copy % 10;
		copy /= 10;
	}

	std::string res = std::to_string(copy) + '.' + expand(r > 0? r : -r, n);

	return res;
}

std::string gen_phone()
{
	std::string res = "";
	res += (uniform_dist(gen) & 1) ? '7' : '8';
	res += "9";
	res += expand(uniform_dist(gen) % 1000000000, 9);
	return res;
}

std::string gen_address()
{
	static std::string cities[] = {"Москва", "Санкт Петербург", "Новосибирск", "Саратовv", "Владивосток"};
	static std::string streets[] = {"Ленинский пр-кт", "улица Ленина", "улица Ельцина", "улица Строителей", "улица Карла-Маркса"};
	int h = (uniform_dist(gen) % 125) + 1;
	return cities[uniform_dist(gen) % 5] + ", " + streets[uniform_dist(gen) % 5] + ", " + std::to_string(h);
}

std::string gen_full_name()
{
	static std::string names[] = {"Иван", "Павел", "Дмитрий", "Виктор", "Владимир", "Мария", "Микола"};
	static std::string surnames[] = {"Иванов", "Кузнецов", "Шевченко", "Кравченко", "Савченко", "Куразов"};

	return surnames[uniform_dist(gen) % 7] + " " + names[uniform_dist(gen) % 6];
}

std::string gen_passport()
{
	std::string res = "45";
	int year = uniform_dist(gen) % 19;
	res += expand(year, 2);

	year = uniform_dist(gen) % 1000000;
	res += expand(year, 6);
	return res;
}

std::string gen_tin(int size)
{
	std::string res = "";
	for(int i = 0; i < size; ++i)
	{
		res += (uniform_dist(gen) % 10) + '0';
	}
	return res;
}

std::string gen_account(int of)
{
	static int groups[] = {40501, 40502, 40503, 40601, 40602, 40603, 40701, 40702, 40703, 40801, 40802, 40803, 40804, 40805, 40806, 40807, 40808, 40809, 40817};
	static int curs[] = {643, 840, 978, 959, 961};
	static int key_koeffs[3] = {7, 1, 3};

	int group = groups[uniform_dist(gen) % 19];
	int cur = curs[uniform_dist(gen) % 5];

	int key = 0;
	int office = of;
	int num = uniform_dist(gen) % 10000000;

	std::string res = expand(group, 5) + expand(cur, 3) + "0" + expand(office, 4) + expand(num, 7);

	for(int i = 0; i < 20; ++i)
	{
		key += (res[i] - '0') * key_koeffs[i % 3];
	}

	key = (key * 3) % 10;

	res[8] = key + '0';

	return res;
}

std::string gen_time()
{
	int year = uniform_dist(gen) % 20 + 2001;
	int mon = uniform_dist(gen) % 12 + 1;
	int day = uniform_dist(gen) % 28 + 1;
	int h = uniform_dist(gen) % 12 + 9;
	int min = uniform_dist(gen) % 60;
	int sec = uniform_dist(gen) % 60;

	return std::to_string(year) + '-' + expand(mon, 2) + '-' + expand(day, 2) + ' ' +
			expand(h, 2) + ':' + expand(min, 2) + ':' + expand(sec, 2);
}

struct Client
{
	unsigned long long id;
	std::string type;
	std::string phone;
	std::string address;

	Client(unsigned long long id_): id(id_), phone(gen_phone()), address(gen_address())
	{
		static int legals = 0;
		type = (uniform_dist(gen) % 5 > 3 && legals < 4) ? (++legals, "legal") : "physical";
	}

	friend std::ostream &operator<<(std::ostream &out, Client &c)
	{
		out << "INSERT INTO Clients(type, phone, address) VALUES('" << c.type << "', '" << c.phone << "', '" << c.address << "');" << std::endl;
		return out;
	}
};

struct Physical_client
{
	unsigned long long id;
	std::string full_name;
	std::string passport;
	std::string tin;

	Physical_client(unsigned long long id_): id(id_), full_name(gen_full_name()), passport(gen_passport()), tin(gen_tin(12)){}

	friend std::ostream &operator<<(std::ostream &out, Physical_client &pc)
	{
		out << "INSERT INTO Physical_clients(full_name, passport, tin) VALUES('" << pc.full_name << "', '" << pc.passport << "', '" << pc.tin << "');" << std::endl;
		return out;
	}
};

struct Legal_client
{
	unsigned long long id;
	std::string name;
	std::string form;
	std::string tin;

	Legal_client(unsigned long long id_): id(id_), tin(gen_tin(10))
	{
		static std::string names[] = {"Рога и копыта", "Диасофт", "Нортон", "МашПромТорг"};
		static std::string forms[] = {"ИП", "ООО", "ОАО", "ЗАО"};

		static int legals = 0;

		name = names[legals];
		form = names[legals];
		++legals;
	}

	friend std::ostream &operator<<(std::ostream &out, Legal_client &lc)
	{
		out << "INSERT INTO Legal_clients(name, form, tin) VALUES('" << lc.name << "', '" << lc.form << "', '" << lc.tin << "');" << std::endl;
		return out;
	}
};

struct Account_type
{
	unsigned long long id;
	std::string name;
	int profitability_percent;
	bool percent;
	long long profitability_fixed;
	bool debiting;
	bool accrual;
	std::string period;

	Account_type(unsigned long long id_, const std::string &name_, int pp, bool perc, long long pf, bool d, bool a, const std::string &per):
		id(id_), name(name_), profitability_percent(pp), percent(perc), profitability_fixed(pf), debiting(d), accrual(a), period(per){}

	friend std::ostream &operator<<(std::ostream &out, Account_type &at)
	{
		out << "INSERT INTO Account_types(name, profitability_percent, profitability_fixed, debiting, accrual, period) VALUES('" << at.name << "', ";

		if(at.percent)
			out << divide(at.profitability_percent, 2) << ", NULL, ";
		else
			out << "NULL, " << divide(at.profitability_fixed, 2) << ", ";

		if(at.debiting)
			out << "TRUE, ";
		else
			out << "FALSE, ";

		if(at.accrual)
			out << "TRUE, '";
		else
			out << "FALSE, '";

		out << at.period << "');" << std::endl;

		return out;
	}
};

struct Account
{
	std::string id;
	unsigned long long client_id;
	unsigned long long type;
	std::string account_status;
	long long balance;
	std::string creating_time;
	bool resp;
	std::string response_account;
	bool loan;
	std::string loan_account;

	Account(unsigned long long office, unsigned long long ci, unsigned long long at, bool resp_, bool loan_): 
		id(gen_account(office)), client_id(ci), type(at), creating_time(gen_time()), resp(resp_), loan(loan_)
	{
		static std::string statuses[] = {"Opened", "Closed", "Frozen"};
		int roullete = uniform_dist(gen) & 8;

		if(roullete < 2)
		{
			account_status = "Frozen";
		}
		else
		{
			account_status = statuses[roullete & 1];
		}

		std::normal_distribution<> bal{5000000, 25000000};

		balance = bal(gen);

		if(resp)
		{
			response_account = gen_account(uniform_dist(gen) % 10000);
		}
		if(loan)
		{
			loan_account = gen_account(uniform_dist(gen) % 10000);
		}
	}

	friend std::ostream &operator<<(std::ostream &out, Account &acc)
	{
		out << "INSERT INTO Accounts(id, client_id, type, status, balance, creating_time, response_account, loan_account) VALUES('";
		out << acc.id << "', " << acc.client_id << ", " << acc.type << ", '" << acc.account_status << "', " << divide(acc.balance, 2) << ", '" << acc.creating_time << "', ";

		if(acc.resp)
			out << '\'' << acc.response_account << "', ";
		else
			out << "NULL, ";

		if(acc.loan)
			out << '\'' << acc.loan_account << "');" << std::endl;
		else
			out << "NULL);" << std::endl;

		return out;
	}

};

struct Office
{
	unsigned long long id;
	std::string phone;
	std::string address;

	Office(unsigned long long id_): id(id_), phone(gen_phone()), address(gen_address()){}

	friend std::ostream &operator<<(std::ostream &out, Office &off)
	{
		out << "INSERT INTO Offices(phone, address) VALUES('" << off.phone << "', '" << off.address << "');" << std::endl;
		return out;
	}
};

struct Transaction
{
	unsigned long long id;
	std::string debit_account_id;
	std::string credit_account_id;
	std::string tran_time;
	unsigned long long amount;

	Transaction(unsigned long long id_, const std::string &da, const std::string &ca): id(id_), debit_account_id(da), credit_account_id(ca), tran_time(gen_time())
	{
		std::normal_distribution<> tran{5000, 1200};
		amount = std::clamp(int(tran(gen)), 0, 100000);
	}

	friend std::ostream &operator<<(std::ostream &out, Transaction &tran)
	{
		out << "INSERT INTO Transactions(debit_account_id, credit_account_id, tran_time, amount) VALUES('" << tran.debit_account_id << "', '" << tran.credit_account_id << "', '";
		out << tran.tran_time << "', " << tran.amount << ");" << std::endl;
		return out;
	}
};


int main()
{
	std::ofstream out("insert.sql");

	//Clients
	constexpr unsigned int clients_amount = 10;

	std::vector<Client> clients;
	std::vector<Legal_client> l_clients;
	std::vector<Physical_client> p_clients;

	for(int i = 0; i < clients_amount; ++i)
	{
		clients.push_back(Client(i + 1));
		out << clients[i];
		if(clients[i].type == "legal")
		{
			l_clients.push_back(Legal_client(i + 1));
			out << l_clients.back() << std::endl;
		}
		else
		{
			p_clients.push_back(Physical_client(i + 1));
			out << p_clients.back() << std::endl;
		}
	}
	out << std::endl;

	//Offices
	constexpr unsigned int offices_amount = 10;
	std::vector<Office> offs;

	for(int i = 0; i < offices_amount; ++i)
	{
		offs.push_back(Office(i + 1));
		out << offs.back();
	}
	out << std::endl;

	//Account's types
	std::vector<Account_type> atypes;
	atypes.push_back(Account_type(1, "Копи и сберегай", 50, true, 0, true, true, "Monthly"));
	atypes.push_back(Account_type(2, "Быстрый старт", -150, true, 0, true, true, "Monthly"));
	atypes.push_back(Account_type(3, "Микрозайм", -300, true, 0, true, true, "Weekly"));
	atypes.push_back(Account_type(4, "Карточный счет", 0, false, 0, true, true, "Annually"));

	for(auto &at: atypes)
	{
		out << at;
	}
	out << std::endl;

	//Accounts
	std::vector<Account> accs;
	for(auto cl: clients)
	{
		int count = 1 + uniform_dist(gen) % 3;
		for(int i = 0; i < count; ++i)
		{
			int type = 1 + uniform_dist(gen) % 4;
			accs.push_back(Account(uniform_dist(gen) % offices_amount, cl.id, type, type != 4, type == 2 || type == 3));
			out << accs.back();
		}
	}
	out << std::endl;

	//Transactions
	constexpr unsigned int tran_amount = 10;
	std::vector<Transaction> trans;

	for(int i = 0; i < tran_amount; ++i)
	{
		if(uniform_dist(gen) & 1 == 0)
		{
			trans.push_back(Transaction(i + 1, accs[uniform_dist(gen) % accs.size()].id, gen_account(uniform_dist(gen) % 10000)));
		}
		else
		{
			trans.push_back(Transaction(i + 1, gen_account(uniform_dist(gen) % 10000), accs[uniform_dist(gen) % accs.size()].id));
		}

		out << trans.back();
	}
	out << std::endl;

	return 0;
}