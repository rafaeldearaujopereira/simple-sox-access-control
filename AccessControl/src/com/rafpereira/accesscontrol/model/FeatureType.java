package com.rafpereira.accesscontrol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The type of a given feature.
 * @author rafaeldearaujopereira
 */
@Entity
@Table(name = "feature_type")
public class FeatureType {

	/** Systems and sub-systems. */
	public static final FeatureType SYSTEM = new FeatureType();

	/** Feature, a CRUD set, a search panel. */
	public static final FeatureType FEATURE = new FeatureType();

	/** A task or procedure that a user can start. */
	public static final FeatureType TASK = new FeatureType();

	/** Menu and sub-menus, it is used to organize features. */
	public static final FeatureType MENU = new FeatureType();

	/** Action, a command that a user can execute. */
	public static final FeatureType ACTION = new FeatureType();

	/**
	 * Access, a special access to a feature, that extends or delimits what a user
	 * can do.
	 */
	public static final FeatureType ACCESS = new FeatureType();

	static {
		SYSTEM.setId(1L);
		FEATURE.setId(2L);
		TASK.setId(3L);
		MENU.setId(4L);
		ACTION.setId(5L);
		ACCESS.setId(6L);
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
