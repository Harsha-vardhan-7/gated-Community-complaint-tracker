package com.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.entity.Complaint;
import com.entity.Resident;
import com.exception.ComplaintNotFoundException;
import com.exception.ResidentNotFoundException;
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

	public void saveComplaint(Complaint complaint) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();

		session.persist(complaint);

		transaction.commit();

	}

	public void updateComplaint(Complaint complaint) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();

		session.merge(complaint);

		transaction.commit();

	}

	public List<Complaint> getComplaintsByResidentId(int residentId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();

		Resident resident = session.find(Resident.class, residentId);

		if (resident == null) {
			throw new ResidentNotFoundException("Resident is Not Found with Resident Id: " + residentId);
		}

		List<Complaint> complaints = resident.getComplaints();

		return complaints;
	}

	public List<Complaint> getAllComplaints() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();

		List<Complaint> complaints = session

				.createQuery("FROM Complaint", Complaint.class)

				.list();

		session.close();

		return complaints;
	}
	
	public List<Complaint> getComplaintsByStatus(String status){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		List<Complaint> complaintsByStatus = session.createQuery("FROM Complaint WHERE status= :status", Complaint.class)
													.setParameter("status", status)
													.getResultList();
		
		session.close();
		
		return complaintsByStatus;
	}
}
