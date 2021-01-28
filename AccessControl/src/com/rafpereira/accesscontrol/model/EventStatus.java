package com.rafpereira.accesscontrol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The status of an event.
 * 
 * @author rafaeldearaujopereira
 */
@Entity
@Table(name = "event_status")
public class EventStatus {

	/** Created (running). */
	public static final EventStatus CREATED = new EventStatus();

	/** OK. */
	public static final EventStatus OK = new EventStatus();

	/** Error. */
	public static final EventStatus ERROR = new EventStatus();

	/** Invalid form (when an event is aborted by a form validation). */
	public static final EventStatus INVALID_FORM = new EventStatus();

	static {
		CREATED.setId(1L);
		OK.setId(2L);
		ERROR.setId(3L);
		INVALID_FORM.setId(4L);
	}

	/** Id. */
	@Id
	@Column(name = "id")
	private Long id;

	/** Description. */
	@Column(name = "description")
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
