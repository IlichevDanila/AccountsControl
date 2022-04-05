package ru.dilichev.AccountControl.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import ru.dilichev.AccountControl.DAO.TransactionDAO;
import ru.dilichev.AccountControl.Models.Transaction;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class TransactionDAOImpl implements TransactionDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactoryBean)
    {
        this.sessionFactory = sessionFactoryBean.getObject();
    }

    @Override
    public void addTransaction(Transaction tran) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(tran);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public String SQLByCondition(Long id, String debit_account_id, String credit_account_id, Timestamp tran_time_low, Timestamp tran_time_high, Double amount_low, Double amount_high) {
        String sql = "FROM Transaction";
        boolean add_and = false;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(id != null || debit_account_id != null || credit_account_id != null || tran_time_low != null ||
                tran_time_high != null || amount_low != null || amount_high != null)
        {
            sql += " WHERE";
            if(id != null)
            {
                sql += " id = " + id;
                add_and = true;
            }
            if(debit_account_id != null)
            {
                if(add_and)
                {
                    sql += " AND";
                }
                sql += " debit_account_id = '" + debit_account_id + "'";
                add_and = true;
            }
            if(credit_account_id != null)
            {
                if(add_and)
                {
                    sql += " AND";
                }
                sql += " credit_account_id = '" + credit_account_id + "'";
                add_and = true;
            }

            if(tran_time_low != null)
            {
                if(tran_time_high != null)
                {
                    if(add_and)
                    {
                        sql += " AND";
                    }
                    sql += " tran_time BETWEEN '" + df.format(tran_time_low) + "' AND '" + df.format(tran_time_high) + "'";
                }
                else
                {
                    if(add_and)
                    {
                        sql += " AND";
                    }
                    sql += " tran_time >= '" + df.format(tran_time_low) + "'";
                }
                add_and = true;
            }
            else if(tran_time_high != null)
            {
                if(add_and)
                {
                    sql += " AND";
                }
                sql += " tran_time <= '" + df.format(tran_time_high) + "'";
                add_and = true;
            }

            if(amount_low != null)
            {
                if(amount_high != null)
                {
                    if(add_and)
                    {
                        sql += " AND";
                    }
                    sql += " amount BETWEEN " + amount_low + " AND " + amount_high;
                }
                else
                {
                    if(add_and)
                    {
                        sql += " AND";
                    }
                    sql += " amount >= " + amount_low;
                }
                add_and = true;
            }
            else if(amount_high != null)
            {
                if(add_and)
                {
                    sql += " AND";
                }
                sql += " amount <= " + amount_high;
                add_and = true;
            }
        }

        return sql;
    }

    @Override
    public List<Transaction> getTransactionByCondition(Long id, String debit_account_id, String credit_account_id,
                                                       Timestamp tran_time_low, Timestamp tran_time_high,
                                                       Double amount_low, Double amount_high) {
        String sql = SQLByCondition(id, debit_account_id, credit_account_id,
                tran_time_low, tran_time_high,
                amount_low, amount_high);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Transaction> res = session.createQuery(sql).list();
        session.close();

        return res;
    }

    @Override
    public String SQLByAccount(String account_id) {
        String sql = "FROM Transaction";
        if(account_id != null)
        {
            sql += " WHERE debit_account_id = '" + account_id + "' OR credit_account_id = '" + account_id + "'";
        }

        return sql;
    }

    @Override
    public List<Transaction> getTransactionByAccount(String account_id) {
        String sql = SQLByAccount(account_id);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Transaction> res = session.createQuery(sql).list();
        session.close();

        return res;
    }
}
