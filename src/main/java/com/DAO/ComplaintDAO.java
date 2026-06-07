package com.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entity.Complaint;
import com.exception.ComplaintNotFoundException;
import com.util.HibernateUtil;

public class ComplaintDAO {

	public Complaint getComplaintWithId(int complaintId) {
		if (complaintId <= 0) {
			throw new ComplaintNotFoundException("Complaint Not Found with Id: " + complaintId);
		}

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Complaint complaint = session.find(Complaint.class, complaintId);

		return complaint;
	}
}
