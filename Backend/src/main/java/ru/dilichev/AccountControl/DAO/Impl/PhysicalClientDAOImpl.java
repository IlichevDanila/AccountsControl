package ru.dilichev.AccountControl.DAO.Impl;

import org.hibernate.Session;
import ru.dilichev.AccountControl.DAO.PhysicalClientDAO;
import ru.dilichev.AccountControl.Models.PhysicalClient;
import ru.dilichev.AccountControl.util.HibernateUtil;

import java.util.List;

public class PhysicalClientDAOImpl implements PhysicalClientDAO {
    @Override
    public void addPhysicalClient(PhysicalClient ph) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(ph.getClient());
        session.save(ph);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deletePhysicalClient(PhysicalClient ph) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(ph);
        session.delete(ph.getClient());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updatePhysicalClient(PhysicalClient ph) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(ph);
        session.update(ph.getClient());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<PhysicalClient> getPhysicalClientByCondition(Long id, String phone, String address, String fullname, String passport, String tin) {
        String sql = "FROM PhysicalClient";
        boolean add_and = false;

        if (phone != null || address != null)
        {
            sql += " JOIN Client USING(id) WHERE Client.type = 'Legal'";
            add_and = true;
        }
        else if(id != null || fullname != null || passport != null || tin != null)
        {
            sql += " WHERE";
        }
        if(id != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " id = " + id;
            add_and = true;
        }
        if(phone != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += "Client.phone = '" + phone + "'";
            add_and = true;
        }
        if(address != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += "Client.address = '" + address + "'";
            add_and = true;
        }
        if(fullname != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += "fullname = '" + fullname + "'";
            add_and = true;
        }
        if(passport != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += "form = '" + passport + "'";
            add_and = true;
        }
        if(tin != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += "tin = '" + tin + "'";
            add_and = true;
        }

        System.out.println(sql);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<PhysicalClient> res = session.createQuery(sql).list();
        session.close();

        return res;
    }
}
