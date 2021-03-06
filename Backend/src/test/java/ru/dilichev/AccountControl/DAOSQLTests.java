package ru.dilichev.AccountControl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dilichev.AccountControl.DAO.*;

import java.sql.Timestamp;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DAOSQLTests {
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

    @Test
    void OfficeDAOTest()
    {
        assertEquals("FROM Office".toLowerCase(Locale.ROOT),
                officeDAO.SQLByCondition(null, null, null).toLowerCase(Locale.ROOT));

        assertEquals("FROM Office WHERE id = 1".toLowerCase(Locale.ROOT),
                officeDAO.SQLByCondition(1L, null, null).toLowerCase(Locale.ROOT));

        assertEquals("FROM Office WHERE phone = '79151992828'".toLowerCase(Locale.ROOT),
                officeDAO.SQLByCondition(null, "79151992828", null).toLowerCase(Locale.ROOT));

        assertEquals("FROM Office WHERE address = 'D123'".toLowerCase(Locale.ROOT),
                officeDAO.SQLByCondition(null, null, "D123").toLowerCase(Locale.ROOT));

        assertEquals("FROM Office WHERE id = 1 AND phone = '79151992828'".toLowerCase(Locale.ROOT),
                officeDAO.SQLByCondition(1L, "79151992828", null).toLowerCase(Locale.ROOT));

        assertEquals("FROM Office WHERE id = 1 AND phone = '79151992828' AND address = 'D123'".toLowerCase(Locale.ROOT),
                officeDAO.SQLByCondition(1L, "79151992828", "D123").toLowerCase(Locale.ROOT));
    }

    @Test
    void TransactionDAOTest()
    {
        assertEquals("FROM Transaction".toLowerCase(Locale.ROOT),
                transactionDAO.SQLByCondition(null, null, null,
                null, null, null, null).toLowerCase(Locale.ROOT));

        assertEquals("FROM Transaction WHERE id = 1".toLowerCase(Locale.ROOT),
                transactionDAO.SQLByCondition(1L, null, null,
                        null, null, null, null).toLowerCase(Locale.ROOT));

        assertEquals("FROM Transaction WHERE id = 1 AND tran_time >= '2022-02-28 10:35:00'".toLowerCase(Locale.ROOT),
                transactionDAO.SQLByCondition(1L, null, null,
                        Timestamp.valueOf("2022-02-28 10:35:00"),
                        null, null, null).toLowerCase(Locale.ROOT));

        assertEquals("FROM Transaction WHERE tran_time BETWEEN '2022-02-28 10:35:00' AND '2022-02-28 10:50:00'".toLowerCase(Locale.ROOT),
                transactionDAO.SQLByCondition(null, null, null,
                        Timestamp.valueOf("2022-02-28 10:35:00"),
                        Timestamp.valueOf("2022-02-28 10:50:00"),
                        null, null).toLowerCase(Locale.ROOT));

        assertEquals("FROM Transaction WHERE tran_time BETWEEN '2022-02-28 10:35:00' AND '2022-02-28 10:50:00' AND amount <= 35000.0".toLowerCase(Locale.ROOT),
                transactionDAO.SQLByCondition(null, null, null,
                        Timestamp.valueOf("2022-02-28 10:35:00"),
                        Timestamp.valueOf("2022-02-28 10:50:00"),
                        null, 35000.0).toLowerCase(Locale.ROOT));

        assertEquals("FROM Transaction WHERE id = 1 AND tran_time BETWEEN '2022-02-28 10:35:00' AND '2022-02-28 10:50:00' AND amount BETWEEN 25000.0 AND 35000.0".toLowerCase(Locale.ROOT),
                transactionDAO.SQLByCondition(1L, null, null,
                        Timestamp.valueOf("2022-02-28 10:35:00"),
                        Timestamp.valueOf("2022-02-28 10:50:00"),
                        25000.0, 35000.0).toLowerCase(Locale.ROOT));

        assertEquals(("FROM Transaction WHERE debit_account_id = '49123456789012345678' " +
                "OR credit_account_id = '49123456789012345678'").toLowerCase(Locale.ROOT),
                transactionDAO.SQLByAccount("49123456789012345678").toLowerCase(Locale.ROOT));
    }

    @Test
    void AccountTypeDAOTest()
    {
        assertEquals("FROM AccountType".toLowerCase(Locale.ROOT),
                accountTypeDAO.SQLByCondition(null, null, null, null, null,
                        null, null, null, null, null).toLowerCase(Locale.ROOT));

        assertEquals("FROM AccountType WHERE id = 1".toLowerCase(Locale.ROOT),
                accountTypeDAO.SQLByCondition(1L, null, null, null, null,
                        null, null, null, null, null).toLowerCase(Locale.ROOT));

        assertEquals("FROM AccountType WHERE valid = 'TRUE' AND profitability_percent BETWEEN 5.0 AND 10.0".toLowerCase(Locale.ROOT),
                accountTypeDAO.SQLByCondition(null, null, 5.0, 10.0, null,
                        null, null, null, null, true).toLowerCase(Locale.ROOT));

        assertEquals(("FROM AccountType WHERE debiting = 'TRUE' AND valid = 'TRUE' AND profitability_fixed >= 12000.0 AND " +
                        "profitability_percent BETWEEN 5.0 AND 10.0").toLowerCase(Locale.ROOT),
                accountTypeDAO.SQLByCondition(null, null, 5.0, 10.0, 12000.0,
                        null, true, null, null, true).toLowerCase(Locale.ROOT));

        assertEquals(("FROM AccountType WHERE id = 1 AND name = 'Some name' AND debiting = 'TRUE' AND accrual = 'FALSE' AND " +
                        "valid = 'TRUE' AND period = 'Daily' AND profitability_fixed BETWEEN 12000.0 AND 24000.0 AND " +
                        "profitability_percent BETWEEN 5.0 AND 10.0").toLowerCase(Locale.ROOT),
                accountTypeDAO.SQLByCondition(1L, "Some name", 5.0, 10.0, 12000.0,
                        24000.0, true, false, "Daily", true).toLowerCase(Locale.ROOT));
    }

    @Test
    void ClientDAOTest()
    {
        assertEquals("FROM Client".toLowerCase(Locale.ROOT),
                clientDAO.SQLByCondition(null, null, null, null).toLowerCase(Locale.ROOT));

        assertEquals("FROM Client WHERE id = 1".toLowerCase(Locale.ROOT),
                clientDAO.SQLByCondition(1L, null, null, null).toLowerCase(Locale.ROOT));

        assertEquals("FROM Client WHERE type = 'Legal'".toLowerCase(Locale.ROOT),
                clientDAO.SQLByCondition(null, "Legal", null, null).toLowerCase(Locale.ROOT));

        assertEquals("FROM Client WHERE phone = '79151992828' AND address = 'D123'".toLowerCase(Locale.ROOT),
                clientDAO.SQLByCondition(null, null, "79151992828", "D123").toLowerCase(Locale.ROOT));

        assertEquals("FROM Client WHERE id = 1 AND type = 'Physical' AND phone = '79151992828' AND address = 'D123'".toLowerCase(Locale.ROOT),
                clientDAO.SQLByCondition(1L, "Physical", "79151992828", "D123").toLowerCase(Locale.ROOT));
    }

    @Test
    void LegalClientDAOTest()
    {
        assertEquals("From LegalClient".toLowerCase(Locale.ROOT),
                legalClientDAO.SQLByCondition(null, null, null, null, null, null).toLowerCase(Locale.ROOT));

        assertEquals("From LegalClient WHERE name = 'Smth' AND form = 'OOO' AND tin = '1234567890'".toLowerCase(Locale.ROOT),
                legalClientDAO.SQLByCondition(null, null, null, "Smth", "OOO", "1234567890").toLowerCase(Locale.ROOT));

        assertEquals(("From LegalClient JOIN Client USING(id) WHERE Client.type = 'Legal' AND " +
                        "Client.phone = '79151992828' AND name = 'Smth' AND form = 'OOO' AND tin = '1234567890'").toLowerCase(Locale.ROOT),
                legalClientDAO.SQLByCondition(null, "79151992828", null, "Smth", "OOO", "1234567890").toLowerCase(Locale.ROOT));
        assertEquals(("From LegalClient JOIN Client USING(id) WHERE Client.type = 'Legal' AND " +
                        "id = 1 AND Client.phone = '79151992828' AND Client.address = 'D123' " +
                        "AND name = 'Smth' AND form = 'OOO' AND tin = '1234567890'").toLowerCase(Locale.ROOT),
                legalClientDAO.SQLByCondition(1L, "79151992828", "D123", "Smth", "OOO", "1234567890").toLowerCase(Locale.ROOT));
    }

    @Test
    void PhysicalClientDAOTest()
    {
        assertEquals("FROM PhysicalClient".toLowerCase(Locale.ROOT),
                physicalClientDAO.SQLByCondition(null, null, null, null, null, null).toLowerCase(Locale.ROOT));

        assertEquals("FROM PhysicalClient WHERE id = 1".toLowerCase(Locale.ROOT),
                physicalClientDAO.SQLByCondition(1L, null, null, null, null, null).toLowerCase(Locale.ROOT));

        assertEquals(("FROM PhysicalClient JOIN Client USING(id) WHERE Client.type = 'Physical' AND " +
                        "Client.phone = '79151992828' AND Client.address = 'D123' AND tin = '123456789012'").toLowerCase(Locale.ROOT),
                physicalClientDAO.SQLByCondition(null, "79151992828", "D123", null, null, "123456789012").toLowerCase(Locale.ROOT));

        assertEquals(("FROM PhysicalClient JOIN Client USING(id) WHERE Client.type = 'Physical' AND id = 1 AND " +
                        "Client.phone = '79151992828' AND Client.address = 'D123' AND fullname = 'Dilichev' " +
                        "AND passport = '4515111111' AND tin = '123456789012'").toLowerCase(Locale.ROOT),
                physicalClientDAO.SQLByCondition(1L, "79151992828", "D123", "Dilichev", "4515111111", "123456789012").toLowerCase(Locale.ROOT));
    }

    @Test
    void AccountDAOTest()
    {
        assertEquals("FROM Account".toLowerCase(Locale.ROOT),
                accountDAO.SQLByCondition(null, null, null, null,
                        null, null, null, null, null, null).toLowerCase(Locale.ROOT));

        assertEquals(("FROM Account WHERE status = 'Frozen' AND " +
                        "response_account = '12345678901234567890' AND " +
                        "creating_time BETWEEN '2020-10-30 10:00:00' AND '2020-11-30 10:00:00'").toLowerCase(Locale.ROOT),
                accountDAO.SQLByCondition(null, "Frozen",  null, null,
                        Timestamp.valueOf("2020-10-30 10:00:00"),
                        Timestamp.valueOf("2020-11-30 10:00:00"),
                        "12345678901234567890",
                        null, null, null).toLowerCase(Locale.ROOT));

        assertEquals(("FROM Account JOIN AccountType WHERE Account.type = AccountType.id AND status = 'Frozen' AND AccountType.name = 'Smth' AND " +
                        "response_account = '12345678901234567890' AND " +
                        "creating_time BETWEEN '2020-10-30 10:00:00' AND '2020-11-30 10:00:00'").toLowerCase(Locale.ROOT),
                accountDAO.SQLByCondition(null, "Frozen",  "Smth", null,
                        Timestamp.valueOf("2020-10-30 10:00:00"),
                        Timestamp.valueOf("2020-11-30 10:00:00"),
                        "12345678901234567890",
                        null, null, null).toLowerCase(Locale.ROOT));
    }
}
