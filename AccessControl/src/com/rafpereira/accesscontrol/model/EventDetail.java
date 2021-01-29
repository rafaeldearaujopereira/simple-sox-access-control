package com.rafpereira.accesscontrol.model;

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
 * The details of an event.
 * @author rafaeldearaujopereira
 */
@Entity
@Table(name = "event_detail")
public class EventDetail {

	/** Id. */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eventDetailSeqGen")
	@SequenceGenerator(name = "eventDetailSeqGen", allocationSize = 1, sequenceName = "event_detail_seq")
	private Long id;

	/** The event that owns the detail. */
	@ManyToOne
	@JoinColumn(name = "event_id", referencedColumnName = "id")
	private Event event;

	/** The name of the field or parameter. */
	@Column(name = "field_name")
	private String fieldName;

	/** The value of the field or parameter. */
	@Column(name = "field_value")
	private String fieldValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	
}
