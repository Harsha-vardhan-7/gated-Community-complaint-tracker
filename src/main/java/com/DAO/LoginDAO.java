package com.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.SelectionQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.entity.Resident;
import com.util.HibernateUtil;

import jakarta.persistence.NoResultException;

public class LoginDAO {

	private static final Logger log = LoggerFactory.getLogger(LoginDAO.class);

	public Resident getResidentDetails(String mobileNumber) {
		log.trace("Entering getResidentDetails method");
		log.debug("Fetching resident details using mobile number");
		Resident resident = getResidentWithMobileNumber(mobileNumber);
		if (resident != null) {
			log.debug("Resident details fetched successfully. Resident ID: {}", resident.getResidentId());
		} else {
			log.debug("No resident found for the provided mobile number");
		}
		return resident;
	}

	private Resident getResidentWithMobileNumber(String mobileNumber) {
		log.trace("Entering getResidentWithMobileNumber method");
		log.debug("Opening Hibernate session to search for resident");
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		log.debug("Creating query to fetch resident by mobile number");
		SelectionQuery<Resident> query = session.createSelectionQuery("from Resident where mobileNumber=?1",
				Resident.class);
		query.setParameter(1, mobileNumber);
		try {
			Resident resident = query.getSingleResult();
			log.debug("Resident found successfully. Resident ID: {}", resident.getResidentId());
			return resident;
		} catch (NoResultException e) {
			log.debug("No resident found for the provided mobile number");
			return null;
		}
	}

	public int getResidentId(String mobileNumber) {
		log.trace("Entering getResidentId method");
		log.debug("Fetching resident details to retrieve resident ID");
		Resident residentWithMobileNumber = getResidentWithMobileNumber(mobileNumber);
		int residentId = residentWithMobileNumber.getResidentId();
		log.debug("Resident ID retrieved successfully: {}", residentId);
		return residentId;
	}

}
