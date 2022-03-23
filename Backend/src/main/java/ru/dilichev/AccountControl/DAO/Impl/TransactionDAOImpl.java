package ru.dilichev.AccountControl.DAO.Impl;

import org.hibernate.Session;
import ru.dilichev.AccountControl.DAO.TransactionDAO;
import ru.dilichev.AccountControl.Models.Transaction;
import ru.dilichev.AccountControl.util.HibernateUtil;

import java.util.List;

public class TransactionDAOImpl implements TransactionDAO {
    @Override
    public void addTransaction(Transaction tran) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(tran);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteTransaction(Transaction tran) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(tran);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateTransaction(Transaction tran) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(tran);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Transaction> getTransactionByCondition(Long id, String debit_account_id, String credit_account_id,
                                                       String tran_time_low, String tran_time_high,
                                                       Double amount_low, Double amount_high) {
        String sql = "FROM Transaction";
        boolean add_and = false;
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
                    sql += " tran_time BETWEEN '" + tran_time_low + "' AND '" + tran_time_high + "'";
                }
                else
                {
                    if(add_and)
                    {
                        sql += " AND";
                    }
                    sql += " tran_time >= '" + tran_time_low + "'";
                }
                add_and = true;
            }
            else if(tran_time_high != null)
            {
                if(add_and)
                {
                    sql += " AND";
                }
                sql += " tran_time <= '" + tran_time_high + "'";
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

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Transaction> res = session.createQuery(sql).list();
        session.close();

        return res;
    }

    @Override
    public List<Transaction> getTransactionByAccount(String account_id) {
        String sql = "FROM Transaction";
        if(account_id != null)
        {
            sql += " WHERE debit_account_id = '" + account_id + "' OR credit_account_id = '" + account_id + "'";
        }

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Transaction> res = session.createQuery(sql).list();
        session.close();

        return res;
    }
}
