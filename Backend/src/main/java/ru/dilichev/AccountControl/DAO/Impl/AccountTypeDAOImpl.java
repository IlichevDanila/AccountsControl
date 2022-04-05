package ru.dilichev.AccountControl.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import ru.dilichev.AccountControl.DAO.AccountTypeDAO;
import ru.dilichev.AccountControl.Models.AccountType;

import java.util.List;

@Repository
public class AccountTypeDAOImpl implements AccountTypeDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactoryBean)
    {
        this.sessionFactory = sessionFactoryBean.getObject();
    }

    @Override
    public void addAccountType(AccountType accType) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(accType);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAccountType(AccountType accType) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(accType);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateAccountType(AccountType accType) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(accType);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public String SQLByCondition(Long id, String name,
                                 Double profitability_percent_low, Double profitability_percent_high,
                                 Double profitability_fixed_low, Double profitability_fixed_high,
                                 Boolean debiting, Boolean accrual, String period, Boolean valid) {
        String sql = "FROM AccountType";
        boolean add_and = false;
        if (id != null || name != null || profitability_fixed_low != null || profitability_fixed_high != null ||
                profitability_percent_low != null || profitability_percent_high != null || debiting != null || accrual != null ||
                period != null || valid != null)
        {
            sql += " WHERE";
        }
        if(id != null)
        {
            sql += " id = " + id;
            add_and = true;
        }
        if(name != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " name = '" + name + "'";
            add_and = true;
        }
        if(debiting != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " debiting = " + (debiting? "'TRUE'" : "'FALSE'");
            add_and = true;
        }
        if(accrual != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " accrual = " + (accrual? "'TRUE'" : "'FALSE'");
            add_and = true;
        }
        if(valid != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " valid = " + (valid? "'TRUE'" : "'FALSE'");
            add_and = true;
        }
        if(period != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " period = '" + period + "'";
            add_and = true;
        }

        if(profitability_fixed_low != null)
        {
            if(profitability_fixed_high != null)
            {
                if(add_and)
                {
                    sql += " AND";
                }
                sql += " profitability_fixed BETWEEN " + profitability_fixed_low + " AND " + profitability_fixed_high;
            }
            else
            {
                if(add_and)
                {
                    sql += " AND";
                }
                sql += " profitability_fixed >= " + profitability_fixed_low;
            }
            add_and = true;
        }
        else if(profitability_fixed_high != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " profitability_fixed <= " + profitability_fixed_high;
            add_and = true;
        }
        if(profitability_percent_low != null)
        {
            if(profitability_percent_high != null)
            {
                if(add_and)
                {
                    sql += " AND";
                }
                sql += " profitability_percent BETWEEN " + profitability_percent_low + " AND " + profitability_percent_high;
            }
            else
            {
                if(add_and)
                {
                    sql += " AND";
                }
                sql += " profitability_percent >= " + profitability_percent_low;
            }
            add_and = true;
        }
        else if(profitability_percent_high != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " profitability_percent <= " + profitability_percent_high;
            add_and = true;
        }

        return sql;
    }

    @Override
    public List<AccountType> getAccountTypeByCondition(Long id, String name,
                                                       Double profitability_percent_low, Double profitability_percent_high,
                                                       Double profitability_fixed_low, Double profitability_fixed_high,
                                                       Boolean debiting, Boolean accrual, String period, Boolean valid) {
        String sql = SQLByCondition(id, name,
                profitability_percent_low, profitability_percent_high,
                profitability_fixed_low, profitability_fixed_high,
                debiting, accrual, period, valid);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<AccountType> res = session.createQuery(sql).list();
        session.close();

        return res;
    }
}
