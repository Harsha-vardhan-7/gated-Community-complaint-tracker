package com.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entity.Resident;
import com.enums.RoleEnum;
import com.util.HibernateUtil;

public class ResidentDAO {

	public Resident saveResident(String firstName, String lastName, int flatNumber, String mobileNumber,
			String password, String roleStr) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();

		Resident resident = new Resident(firstName, lastName, flatNumber, mobileNumber, password);

		// Convert String to RoleEnum
		RoleEnum role = com.enums.RoleEnum.valueOf(roleStr);
		resident.setRole(role);

		session.persist(resident);

		transaction.commit();

		return resident;
	}

	public Resident getResidentWithId(int residentId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		Resident resident = session.find(Resident.class, residentId);

		return resident;
	}
}
