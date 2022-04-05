package ru.dilichev.AccountControl.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import ru.dilichev.AccountControl.DAO.ClientDAO;
import ru.dilichev.AccountControl.DAO.DAOFactory;
import ru.dilichev.AccountControl.DAO.LegalClientDAO;
import ru.dilichev.AccountControl.DAO.PhysicalClientDAO;
import ru.dilichev.AccountControl.Models.Client;
import ru.dilichev.AccountControl.Models.LegalClient;
import ru.dilichev.AccountControl.Models.PhysicalClient;

import java.util.List;

@Repository
public class ClientDAOImpl implements ClientDAO {
    private SessionFactory sessionFactory;

    @Autowired
    private LegalClientDAO legalClientDAO;

    @Autowired
    private PhysicalClientDAO physicalClientDAO;

    @Autowired
    public void setSessionFactory(LocalSessionFactoryBean sessionFactoryBean)
    {
        this.sessionFactory = sessionFactoryBean.getObject();
    }

    @Override
    public void deleteClient(Client sh) {
        if(sh.getType() == "Physical")
        {
            List<PhysicalClient> ph = physicalClientDAO.getPhysicalClientByCondition(sh.getId(),
                    null, null, null, null, null);
            if(ph.size() != 1)
            {
                return;
            }
            physicalClientDAO.deletePhysicalClient(ph.get(0));
        }
        else if(sh.getType() == "Legal")
        {
            List<LegalClient> lh = legalClientDAO.getLegalClientByCondition(sh.getId(),
                    null, null, null, null, null);
            if(lh.size() != 1)
            {
                return;
            }
            legalClientDAO.deleteLegalClient(lh.get(0));
        }
        else
        {
            return;
        }
    }

    @Override
    public void updateClient(Client sh) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Client> old = getClientByCondition(sh.getId(), null, null, null);
        if(old.size() == 1 && old.get(0).getType().equals(sh.getType()))
        {
            session.update(sh);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public String SQLByCondition(Long id, String type, String phone, String address) {
        String sql = "FROM Client";
        boolean add_and = false;

        if(id != null || type != null || phone != null || address != null)
        {
            sql += " WHERE";
        }
        if(id != null)
        {
            sql += " id = " + id;
            add_and = true;
        }
        if(type != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " type = '" + type + "'";
            add_and = true;
        }
        if(phone != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " phone = '" + phone + "'";
            add_and = true;
        }
        if(address != null)
        {
            if(add_and)
            {
                sql += " AND";
            }
            sql += " address = '" + address + "'";
        }

        return sql;
    }

    @Override
    public List<Client> getClientByCondition(Long id, String type, String phone, String address) {
        String sql = SQLByCondition(id, type, phone, address);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Client> res = session.createQuery(sql).list();
        session.close();

        return res;
    }
}
