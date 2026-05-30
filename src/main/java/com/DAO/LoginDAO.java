package com.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.SelectionQuery;

import com.entity.Resident;
import com.util.HibernateUtil;

public class LoginDAO {

	public Resident getResidentDetails(String mobileNumber) {
		return getResidentWithMobileNumber(mobileNumber);

	}

	public Resident getResidentWithMobileNumber(String mobileNumber) {

		if (mobileNumber == null || mobileNumber.isEmpty()) {
			throw new IllegalArgumentException("Mobile number cannot be empty");
		}

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		SelectionQuery<Resident> query = session.createSelectionQuery("from Resident where mobileNumber=?1",
				Resident.class);

		query.setParameter(1, mobileNumber);

		try {
			return query.getSingleResult();
		} catch (jakarta.persistence.NoResultException e) {
			return null;
		}
	}
}
