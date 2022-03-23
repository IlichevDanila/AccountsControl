package ru.dilichev.AccountControl.DAO.Impl;

import org.hibernate.Session;
import ru.dilichev.AccountControl.DAO.LegalClientDAO;
import ru.dilichev.AccountControl.Models.LegalClient;
import ru.dilichev.AccountControl.util.HibernateUtil;

import java.util.List;

public class LegalClientDAOImpl implements LegalClientDAO {
    @Override
    public void addLegalClient(LegalClient lh) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(lh.getClient());
        session.save(lh);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteLegalClient(LegalClient lh) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(lh);
        session.delete(lh.getClient());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateLegalClient(LegalClient lh) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(lh);
        session.update(lh.getClient());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<LegalClient> getLegalClientByCondition(Long id, String phone, String address, String name, String form, String tin) {
        String sql = "FROM LegalClient";
        boolean add_and = false;

        if (phone != null || address != null)
        {
            sql += " JOIN Client USING(id) WHERE Client.type = 'Legal'";
            add_and = true;
        }
        else if(id != null || name != null || form != null || tin != null)
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
        if(name != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += "name = '" + name + "'";
            add_and = true;
        }
        if(form != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += "form = '" + form + "'";
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
        List<LegalClient> res = session.createQuery(sql).list();
        session.close();

        return res;
    }
}
