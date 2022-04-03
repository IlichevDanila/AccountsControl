package ru.dilichev.AccountControl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.dilichev.AccountControl.DAO.DAOFactory;
import ru.dilichev.AccountControl.Models.LegalClient;
import ru.dilichev.AccountControl.Models.Office;
import ru.dilichev.AccountControl.Models.PhysicalClient;
import ru.dilichev.AccountControl.Models.Transaction;

import java.util.List;

@SpringBootApplication
public class AccountControlApplication {

	public static void main(String[] args)
	{
		//SpringApplication.run(AccountControlApplication.class, args);
		List<PhysicalClient> phs = DAOFactory.getPhysicalClientDAO().getPhysicalClientByCondition(null, null,
				null, null, null, null);

		System.out.println(phs.size());
		for(PhysicalClient ph: phs)
		{
			System.out.println(ph.getClient().getPhone());
		}
	}

}
