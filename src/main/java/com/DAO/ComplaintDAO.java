package com.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.entity.Complaint;
import com.entity.Resident;
import com.exception.ComplaintNotFoundException;
import com.exception.ResidentNotFoundException;
import com.util.HibernateUtil;

public class ComplaintDAO {

	private static final Logger log = LoggerFactory.getLogger(ComplaintDAO.class);

	public Complaint getComplaintWithId(int complaintId) {
		log.trace("Entering getComplaintWithId method");
		if (complaintId <= 0) {
			log.warn("Invalid complaint ID received: {}", complaintId);
			throw new ComplaintNotFoundException("Complaint Not Found with Id: " + complaintId);
		}

		log.debug("Opening Hibernate session to fetch complaint with ID: {}", complaintId);
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		Complaint complaint = session.find(Complaint.class, complaintId);
		if (complaint == null) {
			log.warn("No complaint found with ID: {}", complaintId);
		} else {
			log.debug("Complaint fetched successfully with ID: {}", complaintId);
		}
		return complaint;
	}

	public void saveComplaint(Complaint complaint) {
		log.trace("Entering saveComplaint method");
		log.debug("Opening Hibernate session to save new complaint");
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		log.debug("Beginning database transaction for complaint creation");
		Transaction transaction = session.beginTransaction();

		session.persist(complaint);
		log.debug("Complaint persisted successfully. Committing transaction");

		transaction.commit();
		log.info("New complaint saved successfully. Complaint ID: {}", complaint.getComplaintId());
	}

	public void updateComplaint(Complaint complaint) {
		log.trace("Entering updateComplaint method");
		log.debug("Opening Hibernate session to update complaint with ID: {}", complaint.getComplaintId());
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		log.debug("Beginning database transaction for complaint update");
		Transaction transaction = session.beginTransaction();
		session.merge(complaint);
		log.debug("Complaint merged successfully. Committing transaction");

		transaction.commit();
		log.info("Complaint updated successfully. Complaint ID: {}", complaint.getComplaintId());
	}

	public List<Complaint> getComplaintsByResidentId(int residentId) {
		log.trace("Entering getComplaintsByResidentId method");
		log.debug("Opening Hibernate session to fetch complaints for resident ID: {}", residentId);
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		log.debug("Fetching resident with ID: {}", residentId);
		Resident resident = session.find(Resident.class, residentId);
		if (resident == null) {
			log.warn("No resident found with resident ID: {}", residentId);
			throw new ResidentNotFoundException("Resident is Not Found with Resident Id: " + residentId);
		}
		log.debug("Resident found successfully. Fetching associated complaints");

		List<Complaint> complaints = resident.getComplaints();
		log.info("Fetched {} complaints for resident ID: {}", complaints.size(), residentId);
		return complaints;
	}

	public List<Complaint> getAllComplaints() {
		log.trace("Entering getAllComplaints method");
		log.debug("Opening Hibernate session to fetch all complaints");
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		List<Complaint> complaints = session.createQuery("FROM Complaint", Complaint.class).list();
		log.info("Fetched {} complaints from database", complaints.size());

		session.close();

		log.debug("Hibernate session closed successfully");
		return complaints;
	}

	public List<Complaint> getComplaintsByStatus(String status) {
		log.trace("Entering getComplaintsByStatus method");
		log.debug("Opening Hibernate session to fetch complaints with status: {}", status);
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		List<Complaint> complaintsByStatus = session
				.createQuery("FROM Complaint WHERE status= :status", Complaint.class).setParameter("status", status)
				.getResultList();
		log.info("Fetched {} complaints with status: {}", complaintsByStatus.size(), status);

		session.close();
		log.debug("Hibernate session closed successfully");
		return complaintsByStatus;
	}
}
