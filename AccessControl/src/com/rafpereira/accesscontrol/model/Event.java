package com.rafpereira.accesscontrol.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The event registers a single action of an user while using the system.
 * 
 * @author rafaeldearaujopereira
 */
@Entity
@Table(name = "event")
public class Event {

	/** Id. */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eventSeqGen")
	@SequenceGenerator(name = "eventSeqGen", allocationSize = 1, sequenceName = "event_seq")
	private Long id;

	/** The type of the event. */
	@ManyToOne
	@JoinColumn(name = "event_type_id", referencedColumnName = "id")
	private EventType type;

	/** The status of the event. */
	@ManyToOne
	@JoinColumn(name = "event_status_id", referencedColumnName = "id")
	private EventStatus status;

	/** The feature that generated the event. */
	@ManyToOne
	@JoinColumn(name = "feature_id", referencedColumnName = "id")
	private Feature feature;

	/** Event date time. */
	@Column(name = "event_date")
	private Date eventDate;

	/** The versions of the feature. */
	@Transient
	private List<EventDetail> details = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public EventStatus getStatus() {
		return status;
	}

	public void setStatus(EventStatus status) {
		this.status = status;
	}

	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public void setDetails(List<EventDetail> details) {
		this.details = details;
	}

}
