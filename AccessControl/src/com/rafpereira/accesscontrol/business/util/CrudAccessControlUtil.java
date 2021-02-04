package com.rafpereira.accesscontrol.business.util;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rafpereira.data.util.SessionFactoryUtil;

/**
 * The common abstract class with the standard routines to persist CRUD objects.
 * @author rafaeldearaujopereira
 * @param <T> The target class.
 */
public abstract class CrudAccessControlUtil<T> extends AccessControlUtil {

	/**
	 * Obtains the session factory instance related to the current CRUD.
	 * @return Session Factory for the correct connection.
	 */
	public abstract SessionFactoryUtil getSessionFactoryInstance();
	
	/**
	 * Saves the object.
	 * @param t Object.
	 * @return True when successful.
	 */
	public boolean save(T t) {
		Session session = getSessionFactoryInstance().getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			saveInTransaction(t, session);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	/**
	 * The standard behavior to save an object (inside a transaction). Must override to other scenarios.
	 * @param t Object.
	 * @param session The current session.
	 */
	protected void saveInTransaction(T t, Session session) {
		session.saveOrUpdate(t);
	}

	/**
	 * Removes the object.
	 * @param t Object.
	 * @return True when successful.
	 */
	public boolean remove(T t) {
		Session session = getSessionFactoryInstance().getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			removeInTransaction(t, session);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	/**
	 * The standard behavior to remove an object (inside a transaction). Must override when the process must persist children or related items.
	 * @param t Object.
	 * @param session The current session.
	 */
	protected void removeInTransaction(T t, Session session) {
		session.remove(t);
	}

	/**
	 * List objects using filter.
	 * @param filterObj Object containing filter as fields.
	 * @param loadChildren True enable special procedures (that must each be programmed).
	 * @return The list of objects found.
	 */
	public abstract List<T> listByFilter(T filterObj, boolean loadChildren);

	public List<T> listByFilter(T filterObj) {
		return listByFilter(filterObj, false);
	}

	/**
	 * Obtains the object for an update form. Must override when the process must load related items.
	 * @param t Object.
	 * @return Object prepared for an update form.
	 */
	public T getObjectForUpdate(T t) {
		return t;
	}

}
