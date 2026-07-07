package com.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.SelectionQuery;

import com.entity.Resident;
import com.util.HibernateUtil;

import jakarta.persistence.NoResultException;

public class LoginDAO {

	public Resident getResidentDetails(String mobileNumber) {
		return getResidentWithMobileNumber(mobileNumber);

	}

	private Resident getResidentWithMobileNumber(String mobileNumber) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		SelectionQuery<Resident> query = session.createSelectionQuery("from Resident where mobileNumber=?1",
				Resident.class);

		query.setParameter(1, mobileNumber);
		
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public int getResidentId(String mobileNumber) {
		Resident residentWithMobileNumber = getResidentWithMobileNumber(mobileNumber);
		int residentId = residentWithMobileNumber.getResidentId();
		return residentId;
	}
	
}
