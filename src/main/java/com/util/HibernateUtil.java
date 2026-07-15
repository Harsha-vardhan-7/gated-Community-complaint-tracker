package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.controller.LoginServlet;
import com.entity.Complaint;
import com.entity.Resident;

public class HibernateUtil {

	private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

	public static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() {

		log.trace("Entering getSessionFactory method");
		if (sessionFactory == null) {
			log.info("Hibernate SessionFactory is not initialized. Starting initialization");
			try {
				log.debug("Creating Hibernate Configuration object");
				Configuration conf = new Configuration();

				log.debug("Loading Hibernate configuration");
				conf.configure();

				log.debug("Registering annotated entity classes");
				conf.addAnnotatedClass(Resident.class);
				conf.addAnnotatedClass(Complaint.class);

				log.debug("Building Hibernate SessionFactory");
				sessionFactory = conf.buildSessionFactory();
				log.info("Hibernate SessionFactory initialized successfully");
				return sessionFactory;

			} catch (Exception e) {
				log.error("Failed to initialize Hibernate SessionFactory", e);
				throw e;
			}
		} else {
			log.trace("Returning existing Hibernate SessionFactory instance");
			return sessionFactory;
		}
	}
}
