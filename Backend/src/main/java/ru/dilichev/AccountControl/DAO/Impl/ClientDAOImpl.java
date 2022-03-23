package ru.dilichev.AccountControl.DAO.Impl;

import org.hibernate.Session;
import ru.dilichev.AccountControl.DAO.ClientDAO;
import ru.dilichev.AccountControl.DAO.DAOFactory;
import ru.dilichev.AccountControl.Models.Client;
import ru.dilichev.AccountControl.Models.LegalClient;
import ru.dilichev.AccountControl.Models.PhysicalClient;
import ru.dilichev.AccountControl.util.HibernateUtil;

import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    @Override
    public void deleteClient(Client sh) {
        if(sh.getType() == "Physical")
        {
            List<PhysicalClient> ph = DAOFactory.getPhysicalClientDAO().getPhysicalClientByCondition(sh.getId(),
                    null, null, null, null, null);
            if(ph.size() != 1)
            {
                return;
            }
            DAOFactory.getPhysicalClientDAO().deletePhysicalClient(ph.get(0));
        }
        else if(sh.getType() == "Legal")
        {
            List<LegalClient> lh = DAOFactory.getLegalClientDAO().getLegalClientByCondition(sh.getId(),
                    null, null, null, null, null);
            if(lh.size() != 1)
            {
                return;
            }
            DAOFactory.getLegalClientDAO().deleteLegalClient(lh.get(0));
        }
        else
        {
            return;
        }

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(sh);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateClient(Client sh) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(sh);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Client> getClientByCondition(Long id, String type, String phone, String address) {
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

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Client> res = session.createQuery(sql).list();
        session.close();

        return res;
    }
}
