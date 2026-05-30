package com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.entity.Resident;

public class HibernateUtil {

	public static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {

			Configuration conf = new Configuration();
			conf.configure();

		conf.addAnnotatedClass(Resident.class);
			sessionFactory = conf.buildSessionFactory();
			return sessionFactory;
		} else {
			return sessionFactory;
		}
	}
}
