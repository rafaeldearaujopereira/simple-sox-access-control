package com.rafpereira.accesscontrol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feature_type")
public class FeatureType {

	public static final FeatureType SYSTEM = new FeatureType();
	public static final FeatureType FEATURE = new FeatureType();
	public static final FeatureType TASK = new FeatureType();
	public static final FeatureType MENU = new FeatureType();
	public static final FeatureType ACTION = new FeatureType();
	public static final FeatureType ACCESS = new FeatureType();

	static {
		SYSTEM.setId(1L);
		FEATURE.setId(2L);
		TASK.setId(3L);
		MENU.setId(4L);
		ACTION.setId(5L);
		ACCESS.setId(6L);
	}

	@Id
	@Column(name = "id")
	private Long id;

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
