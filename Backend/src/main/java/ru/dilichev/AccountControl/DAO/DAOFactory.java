package ru.dilichev.AccountControl.DAO;

import ru.dilichev.AccountControl.DAO.Impl.OfficeDAOImpl;

public class DAOFactory {
    static private ClientDAO clientDAO = null;
    static private LegalClientDAO legalClientDAO = null;
    static private PhysicalClientDAO physicalClientDAO = null;
    static private AccountTypeDAO accountTypeDAO = null;
    static private AccountDAO accountDAO = null;
    static private OfficeDAO officeDAO = null;
    static private TransactionDAO transactionDAO = null;

    private DAOFactory()
    {}

    static public ClientDAO getClientDAO() {
        return clientDAO;
    }

    static public LegalClientDAO getLegalClientDAO() {
        return legalClientDAO;
    }

    static public PhysicalClientDAO getPhysicalClientDAO() {
        return physicalClientDAO;
    }

    static public AccountTypeDAO getAccountTypeDAO() {
        return accountTypeDAO;
    }

    static public AccountDAO getAccountDAO() {
        return accountDAO;
    }

    static public OfficeDAO getOfficeDAO() {
        if (officeDAO == null)
        {
            officeDAO = new OfficeDAOImpl();
        }
        return officeDAO;
    }

    static public TransactionDAO getTransactionDAO() {
        return transactionDAO;
    }
}
