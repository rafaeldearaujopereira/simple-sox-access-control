package com.rafpereira.accesscontrol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The type of an event.
 * @author rafaeldearaujopereira
 */
@Entity
@Table(name = "event_type")
public class EventType {

	/** Login. */
	public static final EventType LOGIN = new EventType();

	/** Logout. */
	public static final EventType LOGOUT = new EventType();

	/** Create a new item. */
	public static final EventType CREATE = new EventType();

	/** Update an item. */
	public static final EventType UPDATE = new EventType();

	/** Delete an item. */
	public static final EventType DELETE = new EventType();

	/** Search items. */
	public static final EventType SEARCH = new EventType();

	/** Invalid access. */
	public static final EventType INVALID_ACCESS = new EventType();
	
	/** Access to a feature. */
	public static final EventType ACCESS = new EventType();
	
	/** Action, a button or command to a functionality. */
	public static final EventType ACTION = new EventType();

	/** Access on a invalid version of the feature. */
	public static final EventType INVALID_VERSION = new EventType();
	
	
	static {
		LOGIN.setId(1L);
		LOGOUT.setId(2L);
		CREATE.setId(3L);
		UPDATE.setId(4L);
		DELETE.setId(5L);
		SEARCH.setId(6L);
		INVALID_ACCESS.setId(7L);
		ACCESS.setId(8L);
		ACTION.setId(9L);
		INVALID_VERSION.setId(10L);
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
