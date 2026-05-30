package com.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entity.Resident;
import com.util.HibernateUtil;

public class ResidentDAO {

	public  Resident saveResident(String firstName, String lastName, int flatNumber, String mobileNumber, String password) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
			Transaction transaction = session.beginTransaction();
			
			Resident resident = new Resident(firstName, lastName, flatNumber, mobileNumber,password);
			
			session.persist(resident);
			
			transaction.commit();
			
			return resident;
			
	}
}
