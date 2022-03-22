package ru.dilichev.AccountControl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.dilichev.AccountControl.DAO.DAOFactory;
import ru.dilichev.AccountControl.Models.Office;
import ru.dilichev.AccountControl.util.HibernateUtil;

import java.util.List;

@SpringBootApplication
public class AccountControlApplication {

	public static void main(String[] args)
	{
		//SpringApplication.run(AccountControlApplication.class, args);
		List<Office> offices = DAOFactory.getOfficeDAO().getOfficeByCondition(1L, null, null);

		for(Office off: offices)
		{
			System.out.println(off.getAddress());
		}
	}

}
