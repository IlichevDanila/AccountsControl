package ru.dilichev.AccountControl.DAO;

import ru.dilichev.AccountControl.DAO.Impl.*;

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
        if(clientDAO == null)
        {
            clientDAO = new ClientDAOImpl();
        }
        return clientDAO;
    }

    static public LegalClientDAO getLegalClientDAO() {
        if(legalClientDAO == null)
        {
            legalClientDAO = new LegalClientDAOImpl();
        }
        return legalClientDAO;
    }

    static public PhysicalClientDAO getPhysicalClientDAO() {
        if(physicalClientDAO == null)
        {
            physicalClientDAO = new PhysicalClientDAOImpl();
        }
        return physicalClientDAO;
    }

    static public AccountTypeDAO getAccountTypeDAO() {
        /*if(accountTypeDAO == null)
        {
            accountTypeDAO = new AccountTypeDAOImpl();
        }*/
        return accountTypeDAO;
    }

    static public AccountDAO getAccountDAO() {
        /*if(accountDAO == null)
        {
            accountDAO = new AccountDAOImpl();
        }*/
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
        if(transactionDAO == null)
        {
            transactionDAO = new TransactionDAOImpl();
        }
        return transactionDAO;
    }
}
