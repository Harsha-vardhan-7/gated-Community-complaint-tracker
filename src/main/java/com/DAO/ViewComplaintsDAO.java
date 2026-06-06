package com.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entity.Complaint;
import com.entity.Resident;
import com.exception.ResidentNotFoundException;
import com.util.HibernateUtil;

public class ViewComplaintsDAO {

	public List<Complaint> getAllComplaints(int residentId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();

		Resident resident = session.find(Resident.class, residentId);

		if (resident == null) {
			throw new ResidentNotFoundException("Resident is Not Found with Resident Id: " + residentId);
		}

		List<Complaint> complaints = resident.getComplaints();

		return complaints;
	}
}
