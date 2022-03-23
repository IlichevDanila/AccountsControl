package ru.dilichev.AccountControl.DAO.Impl;

import org.hibernate.Session;
import ru.dilichev.AccountControl.DAO.OfficeDAO;
import ru.dilichev.AccountControl.Models.Office;
import ru.dilichev.AccountControl.util.HibernateUtil;

import java.util.List;

public class OfficeDAOImpl implements OfficeDAO {
    @Override
    public void addOffice(Office off)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(off);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteOffice(Office off)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(off);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateOffice(Office off)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(off);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Office> getOfficeByCondition(Long id, String phone, String address)
    {
        String sql = "FROM OFFICE";

        if(id != null || phone != null || address != null) {
            sql += " WHERE";
            if(id != null)
            {
                sql += " id = " + id;
            }
            if(phone != null)
            {
                if(id != null)
                {
                    sql += " AND";
                }
                sql += "phone = '" + phone + "'";
            }
            if(address != null)
            {
                if(id != null || phone != null)
                {
                    sql += " AND";
                }
                sql += "address = '" + address + "'";
            }
        }

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Office> res = session.createQuery(sql).list();
        session.close();

        return res;
    }
}
