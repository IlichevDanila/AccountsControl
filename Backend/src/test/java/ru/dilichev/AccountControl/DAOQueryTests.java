package ru.dilichev.AccountControl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.TestPropertySource;
import ru.dilichev.AccountControl.DAO.*;
import ru.dilichev.AccountControl.Models.*;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource("classpath:application.properties")
public class DAOQueryTests {
    private SessionFactory sessionFactory;

    @Autowired
    OfficeDAO officeDAO;

    @Autowired
    TransactionDAO transactionDAO;

    @Autowired
    ClientDAO clientDAO;

    @Autowired
    LegalClientDAO legalClientDAO;

    @Autowired
    PhysicalClientDAO physicalClientDAO;

    @Autowired
    AccountTypeDAO accountTypeDAO;

    @Autowired
    AccountDAO accountDAO;

    @Autowired
    void setSessionFactory(LocalSessionFactoryBean sessionFactoryBean)
    {
        this.sessionFactory = sessionFactoryBean.getObject();
    }

    @AfterEach
    @BeforeAll
    void empty_tables()
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("TRUNCATE Offices RESTART IDENTITY").executeUpdate();
        session.createSQLQuery("TRUNCATE Transactions RESTART IDENTITY").executeUpdate();
        session.createSQLQuery("TRUNCATE Physical_Clients RESTART IDENTITY").executeUpdate();
        session.createSQLQuery("TRUNCATE Legal_Clients RESTART IDENTITY").executeUpdate();
        session.createSQLQuery("TRUNCATE Clients RESTART IDENTITY CASCADE").executeUpdate();
        session.createSQLQuery("TRUNCATE Accounts RESTART IDENTITY").executeUpdate();
        session.createSQLQuery("TRUNCATE Account_Types RESTART IDENTITY CASCADE").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Test
    void OfficeDAOTest()
    {
        officeDAO.addOffice(new Office(1, "79181992828", "D123"));
        officeDAO.addOffice(new Office(2, "88008565123", "Some address"));
        officeDAO.addOffice(new Office(3, "88008565123", "A new one address"));
        officeDAO.addOffice(new Office(4, "80000000000", "Karl-Marx street, d.86"));

        assertEquals(4, officeDAO.getOfficeByCondition(null, null, null).size());
        assertEquals(1, officeDAO.getOfficeByCondition(1L, null, null).size());
        assertEquals(2, officeDAO.getOfficeByCondition(null, "88008565123", null).size());

        officeDAO.updateOffice(new Office(3, "79000000000", "A new one address"));

        assertEquals(1, officeDAO.getOfficeByCondition(null, "88008565123", null).size());

        officeDAO.deleteOffice(new Office(4, "80000000000", "Karl-Marx street, d.86"));

        assertEquals(0, officeDAO.getOfficeByCondition(null, null, "Karl-Marx street, d.86").size());
    }

    @Test
    void AccountTypeDAOTest()
    {
        accountTypeDAO.addAccountType(new AccountType(1L, "New type", 10.0, null,
                true, false, "Daily", true));
        accountTypeDAO.addAccountType(new AccountType(2L, "Second type", 5.0, null,
                false, true, "Monthly", true));
        accountTypeDAO.addAccountType(new AccountType(3L, "Third type", null, 12500.0,
                true, true, "Daily", true));
        accountTypeDAO.addAccountType(new AccountType(4L, "Fourth type", null, -25000.0,
                false, false, "Annually", false));

        assertEquals(4, accountTypeDAO.getAccountTypeByCondition(null, null,
                null, null, null, null,
                null, null, null, null).size());
        assertEquals(2, accountTypeDAO.getAccountTypeByCondition(null, null,
                null, null, null, null,
                true, null, null, true).size());
        assertEquals(1, accountTypeDAO.getAccountTypeByCondition(null, null,
                null, null, 10000.0, 20000.0,
                null, null, null, true).size());

        accountTypeDAO.updateAccountType(new AccountType(1L, "New type", 10.0, null,
                true, false, "Daily", false));

        assertEquals(0, accountTypeDAO.getAccountTypeByCondition(1L, null,
                null, null, null, null,
                null, null, null, true).size());

        accountTypeDAO.deleteAccountType(new AccountType(1L, "New type", 10.0, null,
                true, false, "Daily", true));

        assertEquals(0, accountTypeDAO.getAccountTypeByCondition(1L, null,
                null, null, null, null,
                null, null, null, null).size());
    }

    @Test
    void ClientDAOTest()
    {
        legalClientDAO.addLegalClient(new LegalClient(new Client(1L, "Legal", "89192192828", "D123"),
                "Company A", "OOO", "1234567890"));
        legalClientDAO.addLegalClient(new LegalClient(new Client(2L, "Legal", "88005553535", "D64"),
                "Company B", "OAO", "0000000000"));
        physicalClientDAO.addPhysicalClient(new PhysicalClient(new Client(3L, "Physical", "79152148377", "Karl-Marx st."),
                "Ivanov Ivan", "3515418956", "123456789012"));
        physicalClientDAO.addPhysicalClient(new PhysicalClient(new Client(4L, "Physical", "79152148377", "Karl-Marx st. D123"),
                "Ivanova maria", "3515419500", "120987654321"));

        assertEquals(4, clientDAO.getClientByCondition(null, null, null, null).size());
        assertEquals(2, clientDAO.getClientByCondition(null, "Legal", null, null).size());
        assertEquals(1, clientDAO.getClientByCondition(4L, null, null, null).size());

        clientDAO.updateClient(new Client(1L, "Legal", "10000000000", "D123"));
        clientDAO.updateClient(new Client(2L, "Physical", "10000000000", "D64"));

        assertEquals(1, clientDAO.getClientByCondition(1L, null, "10000000000", null).size());
        assertEquals(0, clientDAO.getClientByCondition(2L, null, "10000000000", null).size());

        clientDAO.deleteClient(new Client(1L, "Legal", "10000000000", "D123"));

        assertEquals(0, clientDAO.getClientByCondition(1L, null, null, null).size());
    }

    @Test
    void AccountDAOTest()
    {
        AccountType accT1 = new AccountType(1L, "New type", 10.0, null,
                true, false, "Daily", true);
        accountTypeDAO.addAccountType(accT1);

        AccountType accT2 = new AccountType(2L, "Second type", 5.0, null,
                false, true, "Monthly", true);
        accountTypeDAO.addAccountType(accT2);

        Client cl1 = new Client(1L, "Legal", "89192192828", "D123");
        LegalClient lc1 = new LegalClient(cl1, "Company A", "OOO", "1234567890");
        legalClientDAO.addLegalClient(lc1);

        Client cl2 = new Client(2L, "Legal", "88005553535", "D64");
        LegalClient lc2 = new LegalClient(cl2, "Company B", "OAO", "0000000000");
        legalClientDAO.addLegalClient(lc2);

        Client cl3 = new Client(3L, "Physical", "79152148377", "Karl-Marx st.");
        PhysicalClient pc3 = new PhysicalClient(cl3, "Ivanov Ivan", "3515418956", "123456789012");
        physicalClientDAO.addPhysicalClient(pc3);

        accountDAO.addAccount(new Account("40802840914519166017", cl1, accT1, "Opened",
                124.25, Timestamp.valueOf("2010-04-26 16:50:09"), null, null));
        accountDAO.addAccount(new Account("40703978000012244900", cl2, accT2, "Opened",
                12004.25, Timestamp.valueOf("2012-07-11 10:34:12"), null, null));
        accountDAO.addAccount(new Account("40802844444519166017", cl3, accT2, "Frozen",
                0.0, Timestamp.valueOf("2005-05-12 17:44:30"), "40802840914519166017", null));
        accountDAO.addAccount(new Account("40807840900009461040", cl3, accT1, "Opened",
                700000.0, Timestamp.valueOf("2006-05-12 17:44:30"), null, "40802840914519166017"));

        assertEquals(4, accountDAO.getAccountByCondition(null, null, null, null,
                null, null, null, null, null, null).size());
        assertEquals(2, accountDAO.getAccountByCondition(null, null, null, null,
                null, null, null, null, 10000.0, null).size());
        assertEquals(1, accountDAO.getAccountByCondition(null, null, null, null,
                null, null, null, null, 500000.0, 1000000.0).size());
        assertEquals(2, accountDAO.getAccountByCondition(null, null, null, null,
                Timestamp.valueOf("2006-05-12 17:44:30"), Timestamp.valueOf("2010-04-26 16:50:09"), null, null,
                null, null).size());

        accountDAO.updateAccount(new Account("40703978000012244900", cl2, accT2, "Frozen",
                12004.25, Timestamp.valueOf("2012-07-11 10:34:12"), null, null));

        assertEquals(2, accountDAO.getAccountByCondition(null, "Frozen", null, null,
                null, null, null, null, null, null).size());

        accountDAO.deleteAccount(new Account("40703978000012244900", cl2, accT2, "Frozen",
                12004.25, Timestamp.valueOf("2012-07-11 10:34:12"), null, null));

        assertEquals(1, accountDAO.getAccountByCondition(null, "Frozen", null, null,
                null, null, null, null, null, null).size());
    }

    @Test
    void TransactionDAOTest()
    {
        AccountType accT1 = new AccountType(1L, "New type", 10.0, null,
                true, false, "Daily", true);
        accountTypeDAO.addAccountType(accT1);

        AccountType accT2 = new AccountType(2L, "Second type", 5.0, null,
                false, true, "Monthly", true);
        accountTypeDAO.addAccountType(accT2);

        Client cl1 = new Client(1L, "Legal", "89192192828", "D123");
        LegalClient lc1 = new LegalClient(cl1, "Company A", "OOO", "1234567890");
        legalClientDAO.addLegalClient(lc1);

        Client cl2 = new Client(2L, "Legal", "88005553535", "D64");
        LegalClient lc2 = new LegalClient(cl2, "Company B", "OAO", "0000000000");
        legalClientDAO.addLegalClient(lc2);

        Client cl3 = new Client(3L, "Physical", "79152148377", "Karl-Marx st.");
        PhysicalClient pc3 = new PhysicalClient(cl3, "Ivanov Ivan", "3515418956", "123456789012");
        physicalClientDAO.addPhysicalClient(pc3);

        accountDAO.addAccount(new Account("40802840914519166017", cl1, accT1, "Opened",
                124.25, Timestamp.valueOf("2010-04-26 16:50:09"), null, null));
        accountDAO.addAccount(new Account("40703978000012244900", cl2, accT2, "Opened",
                12004.25, Timestamp.valueOf("2012-07-11 10:34:12"), null, null));
        accountDAO.addAccount(new Account("40802844444519166017", cl3, accT2, "Frozen",
                0.0, Timestamp.valueOf("2005-05-12 17:44:30"), "40802840914519166017", null));

        transactionDAO.addTransaction(new Transaction(1L, "40802840914519166017", "40802840914519166333",
                Timestamp.valueOf("2012-07-11 12:33:10"), 500.0));
        transactionDAO.addTransaction(new Transaction(2L, "40802840914519166333", "40802840914519166017",
                Timestamp.valueOf("2010-07-11 12:33:10"), 250.0));
        transactionDAO.addTransaction(new Transaction(3L, "40703978000012244900", "40802844444519166017",
                Timestamp.valueOf("2013-07-11 12:33:10"), 50000.0));

        assertEquals(3, transactionDAO.getTransactionByCondition(null, null, null,
                null, null, null, null).size());
        assertEquals(2, transactionDAO.getTransactionByCondition(null, null, null,
                Timestamp.valueOf("2010-07-11 12:33:10"), Timestamp.valueOf("2012-07-11 12:33:10"), null, null).size());
        assertEquals(1, transactionDAO.getTransactionByCondition(null, null, null,
                null, null, 25000.0, 50000.0).size());
        assertEquals(2, transactionDAO.getTransactionByAccount("40802840914519166017").size());
        assertEquals(1, transactionDAO.getTransactionByAccount("40802844444519166017").size());
    }
}
