package com.rafpereira.accesscontrol.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Feature {


	/** Id. */
	@Id
	@Column(name = "id")
	private Long id;


	/** Name. */
	@Column(name = "name")
	private String name;


	/** Description. */
	@Column(name = "description")
	private String description;

	
}
