package com.rafpereira.accesscontrol.data.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * The abstract class for the Hibernate ORM session factory throughout Access Control and other user systems.
 * @author rafaeldearaujopereira
 */
public abstract class SessionFactoryUtil {

	/**
	 * The current implementation (for the user system).
	 */
	protected static SessionFactoryUtil sessionFactoryUtil;

	/**
	 * Obtains a Hibernate session.
	 * @return
	 */
	public abstract Session getSession();

	/**
	 * The method must be implemented by each user system, defining the specific list of entity classes.
	 * @return The configuration, defining which entities are used for a given user system.
	 */
	protected abstract Configuration getConfiguration();

	/**
	 * Obtains the current implementation of the session factory.
	 * @return The session factory.
	 */
	public static SessionFactoryUtil getInstance() {
		return sessionFactoryUtil;
	}

	/**
	 * Obtains the session factory.
	 * @return The session factory.
	 */
	protected abstract SessionFactory getFactory();
	
	/**
	 * Closes the session factory.
	 */
	public void close() {
		getFactory().close();
	}

}
