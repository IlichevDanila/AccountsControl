package ru.dilichev.AccountControl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.dilichev.AccountControl.util.HibernateUtil;

@SpringBootApplication
public class AccountControlApplication {

	public static void main(String[] args)
	{
		//SpringApplication.run(AccountControlApplication.class, args);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Client");
		System.out.println(query.list().size());
		session.close();
	}

}
