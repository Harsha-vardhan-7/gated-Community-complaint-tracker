package com.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.entity.Resident;
import com.enums.RoleEnum;
import com.util.HibernateUtil;

public class ResidentDAO {

	private static final Logger log = LoggerFactory.getLogger(ResidentDAO.class);

	public Resident saveResident(String firstName, String lastName, int flatNumber, String mobileNumber,

			String password, String roleStr) {
		log.trace("Entering saveResident method");
		log.debug("Opening Hibernate session to save new resident");
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		log.debug("Beginning database transaction for resident registration");
		Transaction transaction = session.beginTransaction();

		log.debug("Creating new Resident object");
		Resident resident = new Resident(firstName, lastName, flatNumber, mobileNumber, password);

		log.debug("Converting role value to RoleEnum: {}", roleStr);
		// Convert String to RoleEnum
		RoleEnum role = com.enums.RoleEnum.valueOf(roleStr);
		resident.setRole(role);

		log.debug("Persisting new resident with flat number: {} and role: {}", flatNumber, role);
		session.persist(resident);

		log.debug("Resident persisted successfully. Committing transaction");
		transaction.commit();

		log.info("Resident registered successfully. Resident ID: {}, Flat Number: {}, Role: {}",
				resident.getResidentId(), flatNumber, role);
		return resident;
	}

	public Resident getResidentWithId(int residentId) {
		log.trace("Entering getResidentWithId method");
		log.debug("Opening Hibernate session to fetch resident with ID: {}", residentId);
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		Resident resident = session.find(Resident.class, residentId);
		if (resident == null) {
			log.debug("No resident found with ID: {}", residentId);
		} else {
			log.debug("Resident fetched successfully with ID: {}", residentId);
		}
		return resident;
	}
}
