package com.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entity.Complaint;
import com.util.HibernateUtil;

public class ComplaintSubmitDAO {
	public void saveComplaint(Complaint complaint) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();

		session.persist(complaint);

		transaction.commit();

	}
}
