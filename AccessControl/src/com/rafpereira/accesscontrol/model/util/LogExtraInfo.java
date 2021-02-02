package com.rafpereira.accesscontrol.model.util;

import java.util.Date;

import com.rafpereira.accesscontrol.model.Event;
import com.rafpereira.accesscontrol.model.Session;
import com.rafpereira.accesscontrol.model.User;

public class LogExtraInfo {

	private Date requestDate;

	private String ipAddress;

	private String hostName;

	private String externalSessionId;

	private User user;
	
	private Session session;

	private Event event;
	
	

	public LogExtraInfo(String ipAddress, String hostName, String token) {
		this.requestDate = new Date();
		this.ipAddress = ipAddress;
		this.hostName = hostName;
		this.externalSessionId = token;
	}

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
