package ru.dilichev.AccountControl.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import ru.dilichev.AccountControl.DAO.OfficeDAO;
import ru.dilichev.AccountControl.Models.Office;

import java.util.List;

@Repository
public class OfficeDAOImpl implements OfficeDAO {
    private SessionFactory sessionFactory;

    @Autowired
    @Qualifier("sessionFactoryBean")
    public void setSessionFactory(LocalSessionFactoryBean sessionFactoryBean)
    {
        this.sessionFactory = sessionFactoryBean.getObject();
    }

    @Override
    public void addOffice(Office off)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(off);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteOffice(Office off)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(off);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateOffice(Office off)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(off);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public String SQLByCondition(Long id, String phone, String address) {
        String sql = "FROM Office";

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
                sql += " phone = '" + phone + "'";
            }
            if(address != null)
            {
                if(id != null || phone != null)
                {
                    sql += " AND";
                }
                sql += " address = '" + address + "'";
            }
        }

        return sql;
    }

    @Override
    public List<Office> getOfficeByCondition(Long id, String phone, String address)
    {
        String sql = SQLByCondition(id, phone, address);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Office> res = session.createQuery(sql).list();
        session.close();

        return res;
    }
}
