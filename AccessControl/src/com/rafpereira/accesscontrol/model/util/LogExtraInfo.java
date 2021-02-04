package com.rafpereira.accesscontrol.model.util;

import java.util.Date;

import com.rafpereira.accesscontrol.model.Event;
import com.rafpereira.accesscontrol.model.Session;
import com.rafpereira.accesscontrol.model.User;

/**
 * A bean that contains the information used to create and update events or sessions.
 * @author rafaeldearaujopereira
 */
public class LogExtraInfo {

	/** The request date and time. */
	private Date requestDate;

	/** Ip Address. */
	private String ipAddress;

	/** Host name. */
	private String hostName;

	/** Session Id or Token from an external system. */
	private String externalSessionId;

	/** User. */
	private User user;
	
	/** Session. */
	private Session session;

	/** Event. */
	private Event event;
	
	/**
	 * Constructor that receives information required to create a session.
	 * @param ipAddress Id address.
	 * @param hostName Host name.
	 * @param token External Id.
	 */
	public LogExtraInfo(String ipAddress, String hostName, String token) {
		this.requestDate = new Date();
		this.ipAddress = ipAddress;
		this.hostName = hostName;
		this.externalSessionId = token;
	}

	/**
	 * Constructor that receives an already created session.
	 * @param session Session.
	 */
	public LogExtraInfo(Session session) {
		this.requestDate = new Date();
		this.session = session;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getExternalSessionId() {
		return externalSessionId;
	}

	public void setExternalSessionId(String externalSessionId) {
		this.externalSessionId = externalSessionId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
