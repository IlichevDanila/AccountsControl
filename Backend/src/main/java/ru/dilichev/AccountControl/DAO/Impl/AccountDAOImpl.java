package ru.dilichev.AccountControl.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import ru.dilichev.AccountControl.DAO.AccountDAO;
import ru.dilichev.AccountControl.Models.Account;

import java.util.List;

public class AccountDAOImpl implements AccountDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactoryBean)
    {
        sessionFactory = sessionFactoryBean.getObject();
    }

    @Override
    public void addAccount(Account acc) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(acc);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAccount(Account acc) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(acc);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateAccount(Account acc) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(acc);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public String SQLByCondition(String id, String status, String typeName, Long clientId,
                                 String creating_time_low, String creating_time_high,
                                 String response_account, String loan_account,
                                 Double balance_low, Double balance_high) {
        String sql = "FROM Account";
        boolean add_and = false;
        if (typeName != null) {
            sql += " JOIN AccountType WHERE Account.type = AccountType.id";
            add_and = true;
        }
        else if(id != null || status != null || clientId != null || creating_time_low != null ||
        creating_time_high != null || response_account != null || loan_account != null || balance_low != null ||
        balance_high != null)
        {
            sql += " WHERE";
        }

        if(id != null)
        {
            sql += " id = " + id;
            add_and = true;
        }
        if(status != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " status = '" + status + "'";
            add_and = true;
        }
        if(typeName != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " AccountType.name = '" + typeName + "'";
            add_and = true;
        }
        if(clientId != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " clientId = " + clientId;
            add_and = true;
        }
        if(response_account != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " response_account = '" + response_account + "'";
            add_and = true;
        }
        if(loan_account != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " loan_account = '" + loan_account + "'";
            add_and = true;
        }

        if(creating_time_low != null)
        {
            if(creating_time_high != null)
            {
                if(add_and)
                {
                    sql += " AND";
                }
                sql += " creating_time BETWEEN '" + creating_time_low + "' AND '" + creating_time_high + "'";
            }
            else
            {
                if(add_and)
                {
                    sql += " AND";
                }
                sql += " creating_time >= '" + creating_time_low + "'";
            }
            add_and = true;
        }
        else if(creating_time_high != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " creating_time <= '" + creating_time_high + "'";
            add_and = true;
        }
        if(balance_low != null)
        {
            if(balance_high != null)
            {
                if(add_and)
                {
                    sql += " AND";
                }
                sql += " balance BETWEEN " + balance_low + " AND " + balance_high;
            }
            else
            {
                if(add_and)
                {
                    sql += " AND";
                }
                sql += " balance >= " + balance_low;
            }
            add_and = true;
        }
        else if(balance_high != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " balance <= " + balance_high;
            add_and = true;
        }

        return sql;
    }

    @Override
    public List<Account> getAccountByCondition(String id, String status, String typeName, Long clientId,
                                               String creating_time_low, String creating_time_high,
                                               String response_account, String loan_account,
                                               Double balance_low, Double balance_high) {
        String sql = SQLByCondition(id, status, typeName, clientId,
                creating_time_low, creating_time_high,
                response_account, loan_account,
                balance_low, balance_high);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Account> res = session.createQuery(sql).list();
        session.close();

        return res;
    }
}
