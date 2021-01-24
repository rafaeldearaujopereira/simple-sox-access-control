package com.rafpereira.accesscontrol.business.util;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rafpereira.data.util.SessionFactoryUtil;

public abstract class CrudAccessControlUtil<T> extends AccessControlUtil {

	public abstract SessionFactoryUtil getSessionFactoryInstance();
	
	public boolean save(T t) {
		Session session = getSessionFactoryInstance().getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			saveInTransaction(t, session);
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

	protected void saveInTransaction(T t, Session session) {
		session.saveOrUpdate(t);
	}

	public boolean remove(T t) {
		Session session = getSessionFactoryInstance().getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			removeInTransaction(t, session);
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

	protected void removeInTransaction(T t, Session session) {
		session.remove(t);
	}

	public abstract List<T> listByFilter(T filterObj, boolean loadChildren);

	public List<T> listByFilter(T filterObj) {
		return listByFilter(filterObj, false);
	}

	public T getObjectForUpdate(T t) {
		return t;
	}

}
