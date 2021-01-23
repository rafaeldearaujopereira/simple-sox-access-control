package com.rafpereira.accesscontrol.data.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.rafpereira.accesscontrol.model.FeatureType;

public class AccessControlSessionFactoryUtil extends SessionFactoryUtil {

	private static SessionFactory factory;
	
	private static ServiceRegistry serviceRegistry;
	
	private static SessionFactory getSessionFactory() {
		if (factory == null && sessionFactoryUtil != null) {
			Configuration config = sessionFactoryUtil.getConfiguration();
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
			factory = config.buildSessionFactory(serviceRegistry);
		}
		return factory;
	}
	
	@Override
	public Session getSession() {
		return getSessionFactory().openSession();
	}

	@Override
	protected Configuration getConfiguration() {
		Configuration config = new Configuration();
		config.configure();
		config.addAnnotatedClass(FeatureType.class);
		return config;
	}

	public AccessControlSessionFactoryUtil() {
		if (sessionFactoryUtil == null) {
			sessionFactoryUtil = this;
		}
	}

	@Override
	protected SessionFactory getFactory() {
		return factory;
	}
	
}
