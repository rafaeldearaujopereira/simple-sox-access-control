package com.rafpereira.accesscontrol.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The user's session (login to logout).
 * 
 * @author rafaeldearaujopereira
 */
@Entity
@Table(name = "event")
public class Session {

	/** Id. */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessionSeqGen")
	@SequenceGenerator(name = "sessionSeqGen", allocationSize = 1, sequenceName = "session_seq")
	private Long id;

	/** Id of the session on an external system. */
	private String externalId;

	/** The user that owns the session. */
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	/** Session start time. */
	@Column(name = "start_date")
	private Date startDate;

	/** Session end time. */
	@Column(name = "end_date")
	private Date endDate;

	/** IP address. */
	@Column(name = "ip_address")
	private String ipAddress;

	/** Host name. */
	@Column(name = "host_name")
	private String hostName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

}
