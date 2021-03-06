package com.rafpereira.accesscontrol.data.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.rafpereira.accesscontrol.model.Event;
import com.rafpereira.accesscontrol.model.EventDetail;
import com.rafpereira.accesscontrol.model.EventStatus;
import com.rafpereira.accesscontrol.model.EventType;
import com.rafpereira.accesscontrol.model.Feature;
import com.rafpereira.accesscontrol.model.FeatureType;
import com.rafpereira.accesscontrol.model.Role;
import com.rafpereira.accesscontrol.model.User;
import com.rafpereira.accesscontrol.model.Version;
import com.rafpereira.data.util.SessionFactoryUtil;

public class AccessControlSessionFactoryUtil extends SessionFactoryUtil {

	/** The current implementation (for the user system). */
	protected static SessionFactoryUtil sessionFactoryUtil;

	/** Session factory. */
	private static SessionFactory factory;
	
	/** Session registry. */
	private static ServiceRegistry serviceRegistry;
	
	/**
	 * Obtains the current implementation of the session factory.
	 * @return The session factory.
	 */
	public static SessionFactoryUtil getInstance() {
		return sessionFactoryUtil;
	}
	
	/**
	 * Obtains the session factory, or creates it.
	 * @return The session factory.
	 */
	private static SessionFactory getSessionFactory() {
		if (factory == null && sessionFactoryUtil != null) {
			Configuration config = sessionFactoryUtil.getConfiguration();
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			factory = config.buildSessionFactory(serviceRegistry);
		}
		return factory;
	}
	
	/**
	 * Obtains a session from the session factory.
	 * return A Hibernate session.
	 */
	@Override
	public Session getSession() {
		return getSessionFactory().openSession();
	}

	/**
	 * Configures the Hibernate.
	 * return The Hibernate configuration.
	 */
	@Override
	public Configuration getConfiguration() {
		Configuration config = new Configuration();
		config.configure("classpath:/com/rafpereira/accesscontrol/config/hibernate.cfg.xml");
		config.addAnnotatedClass(FeatureType.class);
		config.addAnnotatedClass(EventType.class);
		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(Version.class);
		config.addAnnotatedClass(Role.class);
		config.addAnnotatedClass(Feature.class);
		config.addAnnotatedClass(com.rafpereira.accesscontrol.model.Session.class);
		config.addAnnotatedClass(EventStatus.class);
		config.addAnnotatedClass(Event.class);
		config.addAnnotatedClass(EventDetail.class);
		return config;
	}

	/**
	 * Constructor, that initializes the Singleton.
	 */
	public AccessControlSessionFactoryUtil() {
		if (sessionFactoryUtil == null) {
			sessionFactoryUtil = this;
		}
	}

	/**
	 * Obtains the session factory.
	 * return Session factory.
	 */
	@Override
	protected SessionFactory getFactory() {
		return factory;
	}
	
}
