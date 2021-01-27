package com.rafpereira.accesscontrol.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * A feature (a system, or a menu, or a button in a window).
 * @author rafaeldearaujopereira
 */
@Entity
@Table(name = "feature")
public class Feature {

	/** Id. */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "featureSeqGen")
	@SequenceGenerator(name = "featureSeqGen", allocationSize = 1, sequenceName = "feature_seq")
	private Long id;

	/** The parent feature. */
	@ManyToOne
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	private Feature parent;
	
	/** The feature type. */
	@ManyToOne
	@JoinColumn(name = "feature_type_id", referencedColumnName = "id")
	private FeatureType type;

	/** Code (use to link 'logically', the implementation on the access control). */
	@Column(name = "code")
	private String code;

	/** Name. */
	@Column(name = "name")
	private String name;

	/** Description. */
	@Column(name = "description")
	private String description;

	/** The roles with access to the feature. */
	@ManyToMany(mappedBy = "features")
	private List<Role> roles = new ArrayList<>();
	
	/** The owner user id (when it exists). */
	@ManyToOne
	@JoinColumn(name = "owner_id", referencedColumnName = "id")
	private User owner;

	/** The children features. */
	@Transient
	private List<Feature> children = new ArrayList<>();

	/** The versions of the feature. */
	@Transient
	private List<Version> versions = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Feature getParent() {
		return parent;
	}

	public void setParent(Feature parent) {
		this.parent = parent;
	}

	public FeatureType getType() {
		return type;
	}

	public void setType(FeatureType type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Feature> getChildren() {
		return children;
	}

	public void setChildren(List<Feature> children) {
		this.children = children;
	}

	public List<Version> getVersions() {
		return versions;
	}

	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}

}
