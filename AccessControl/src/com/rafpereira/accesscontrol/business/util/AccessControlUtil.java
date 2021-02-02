package com.rafpereira.accesscontrol.business.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rafpereira.accesscontrol.data.util.AccessControlSessionFactoryUtil;
import com.rafpereira.accesscontrol.model.Event;
import com.rafpereira.accesscontrol.model.EventDetail;
import com.rafpereira.accesscontrol.model.EventStatus;
import com.rafpereira.accesscontrol.model.EventType;
import com.rafpereira.accesscontrol.model.Feature;
import com.rafpereira.accesscontrol.model.User;
import com.rafpereira.accesscontrol.model.util.LogExtraInfo;

/**
 * The main util class for access control throughout the systems.
 * It will be responsible for "internal" login
 * 
 * @author rafaeldearaujopereira
 */
public class AccessControlUtil {

	/** Map to avoid excessive queries on the database (for user mapping searches). */
	private HashMap<String, User> usersByLogin = null;
	
	/** Map of all features by theirs codes. */ 
	private HashMap<String, Feature> featuresByCode = null;
	
	/**
	 * Login: check if the user exists, and then registers a login event.
	 * The authentication "de facto" will be always dealt by another structure.
	 * @param userLogin User login
	 * @param logInfo Additional info
	 * @return The user (when exists)
	 */
	public User login(String userLogin, LogExtraInfo logInfo) {
		Session session = AccessControlSessionFactoryUtil.getInstance().getSession();
		TypedQuery<User> query = session.createQuery("from User u where u.login = :login and u.active = :active", User.class);
		query.setParameter("login", userLogin);
		query.setParameter("active", Boolean.TRUE);
		List<User> users = query.getResultList();
		User user = (users.size() == 1) ? users.get(0) : null;
		logInfo.setUser(user);
		if (user != null) {
			registerLoginLogout(userLogin, logInfo, EventType.LOGIN);
		} else {
			registerInvalidAccess("LOGIN", userLogin, logInfo);
		}
		return user;
	}

	/**
	 * Logout: registers the logout event.
	 * @param logInfo Additional info
	 */
	public void logout(LogExtraInfo logInfo) {
		registerLoginLogout(null, logInfo, EventType.LOGOUT);
	}
	
	/**
	 * Registers a login or logout event.
	 * @param userLogin User login (for login event)
	 * @param logInfo Additional info
	 * @param type The type of the event
	 */
	private void registerLoginLogout(String userLogin, LogExtraInfo logInfo, EventType type) {
		Session session = AccessControlSessionFactoryUtil.getInstance().getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Event event = new Event(logInfo);
			event.setType(type);
			if (userLogin != null) {
				EventDetail eventDetail = new EventDetail();
				eventDetail.setEvent(event);
				eventDetail.setFieldName("userLogin");
				eventDetail.setFieldValue(userLogin);
				event.getDetails().add(eventDetail);

				com.rafpereira.accesscontrol.model.Session userSession = new com.rafpereira.accesscontrol.model.Session();
				userSession.setUser(getUserByLogin(userLogin));
				userSession.setStartDate(new Date());
				userSession.setExternalId(logInfo.getExternalSessionId());
				userSession.setIpAddress(logInfo.getIpAddress());
				userSession.setHostName(logInfo.getHostName());
				logInfo.setSession(userSession);
				session.save(userSession);
				event.setSession(userSession);
			} else {
				logInfo.getSession().setEndDate(logInfo.getRequestDate());
				session.saveOrUpdate(logInfo.getSession());
			}
			event.setStatus((event.getSession() != null) ? EventStatus.OK : EventStatus.ERROR);
			session.save(event);
			for (EventDetail eventDetail : event.getDetails()) {
				session.save(eventDetail);
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) tx.rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * Obtains the user by login.
	 * @param userLogin User login
	 * @return User
	 */
	public User getUserByLogin(String userLogin) {
		if (usersByLogin == null || usersByLogin.get(userLogin) == null) {
			usersByLogin = new HashMap<>();
			Session session = AccessControlSessionFactoryUtil.getInstance().getSession();
			TypedQuery<User> query = session.createQuery("from User u", User.class);
			List<User> users = query.getResultList();
			session.close();
			users.forEach(user -> usersByLogin.put(user.getLogin(), user));
		}
		return usersByLogin.get(userLogin);
	}

	/**
	 * Obtains the feature by code.
	 * @param code The code of the feature
	 * @return Feature
	 */
	public Feature getFeatureByCode(String code) {
		if (featuresByCode == null || featuresByCode.get(code) == null) {
			featuresByCode = new HashMap<>();
			Session session = AccessControlSessionFactoryUtil.getInstance().getSession();
			TypedQuery<Feature> query = session.createQuery("from Feature f", Feature.class);
			List<Feature> features = query.getResultList();
			session.close();
			features.forEach(feature -> featuresByCode.put(feature.getCode(), feature));
		}
		return featuresByCode.get(code);
	}
	
	
	/**
	 * Updates a registered event.
	 * @param event Event
	 */
	public void updateEvent(Event event) {
		Session session = AccessControlSessionFactoryUtil.getInstance().getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(event);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) tx.rollback();
		} finally {
			session.close();
		}
	}

	/**
	 * Updates a registered event.
	 * @param event Event
	 */
	public Event registerEvent(String featureCode, LogExtraInfo logInfo, EventType type, EventStatus status, ArrayList<EventDetail> details) {
		Session session = AccessControlSessionFactoryUtil.getInstance().getSession();
		Transaction tx = null;
		Event event = null;
		try {
			tx = session.beginTransaction();
			event = new Event(logInfo);
			event.setFeature(getFeatureByCode(featureCode));
			event.setType(type);
			event.setStatus(status);
			logInfo.setEvent(event);
			session.save(event);
			if (details != null) {
				for (EventDetail detail : details) {
					detail.setEvent(event);
					session.save(detail);
				}
			}
			
			session.saveOrUpdate(event);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) tx.rollback();
		} finally {
			session.close();
		}
		return event;
	}
	
	/**
	 * Register an invalid access.
	 * @param featureCode The code of the feature that the user tried to access
	 * @param content The user name for login events, or session token for authenticated users trying to do an invalid access.
	 * @param logInfo Additional info
	 */
	public void registerInvalidAccess(String featureCode, String content, LogExtraInfo logInfo) {
		ArrayList<EventDetail> details = new ArrayList<>();
		if (content != null) {
			EventDetail eventDetail = new EventDetail();
			eventDetail.setFieldName(featureCode);
			eventDetail.setFieldValue(content);
			details.add(eventDetail);
		}
		registerEvent(featureCode, logInfo, EventType.INVALID_ACCESS, EventStatus.ERROR, details);
	}
	
	/**
	 * Register an access that the user was using an old version.
	 * @param featureCode The code of the feature that the user tried to access
	 * @param logInfo Additional info
	 */
	public void registerInvalidVersion(String featureCode, LogExtraInfo logInfo) {
		registerEvent(featureCode, logInfo, EventType.INVALID_VERSION, EventStatus.ERROR, null);
	}
	
	/**
	 * Register an access that was successful.
	 * @param featureCode The code of the feature that was accessed.
	 * @param logInfo Additional info
	 */
	public void registerAccess(String featureCode, LogExtraInfo logInfo) {
		registerEvent(featureCode, logInfo, EventType.ACCESS, EventStatus.OK, null);
	}
	
}
