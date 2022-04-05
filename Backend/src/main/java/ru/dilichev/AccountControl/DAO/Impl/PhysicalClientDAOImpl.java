package ru.dilichev.AccountControl.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import ru.dilichev.AccountControl.DAO.PhysicalClientDAO;
import ru.dilichev.AccountControl.Models.PhysicalClient;

import java.util.List;

@Repository
public class PhysicalClientDAOImpl implements PhysicalClientDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactoryBean)
    {
        this.sessionFactory = sessionFactoryBean.getObject();
    }

    @Override
    public void addPhysicalClient(PhysicalClient ph) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(ph.getClient());
        session.save(ph);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deletePhysicalClient(PhysicalClient ph) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(ph);
        session.delete(ph.getClient());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updatePhysicalClient(PhysicalClient ph) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(ph);
        session.update(ph.getClient());
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public String SQLByCondition(Long id, String phone, String address, String fullname, String passport, String tin) {
        String sql = "FROM PhysicalClient";
        boolean add_and = false;

        if (phone != null || address != null)
        {
            sql += " JOIN Client USING(id) WHERE Client.type = 'Physical'";
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
            sql += " Client.phone = '" + phone + "'";
            add_and = true;
        }
        if(address != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " Client.address = '" + address + "'";
            add_and = true;
        }
        if(fullname != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " fullname = '" + fullname + "'";
            add_and = true;
        }
        if(passport != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " passport = '" + passport + "'";
            add_and = true;
        }
        if(tin != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " tin = '" + tin + "'";
            add_and = true;
        }

        return sql;
    }

    @Override
    public List<PhysicalClient> getPhysicalClientByCondition(Long id, String phone, String address, String fullname, String passport, String tin) {
        String sql = SQLByCondition(id, phone, address, fullname, passport, tin);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<PhysicalClient> res = session.createQuery(sql).list();
        session.close();

        return res;
    }
}
