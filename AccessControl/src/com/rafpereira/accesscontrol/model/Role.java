package com.rafpereira.accesscontrol.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The role that a user can performs in the organization.
 * 
 * @author rafaeldearaujopereira
 */
@Entity
@Table(name = "role")
public class Role {

	/** Id. */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSeqGen")
	@SequenceGenerator(name = "roleSeqGen", allocationSize = 1, sequenceName = "role_seq")
	private Long id;

	/** Name. */
	@Column(name = "name")
	private String name;

	/** Description. */
	@Column(name = "description")
	private String description;

	/** Users that are assigned to the role. */
	@ManyToMany(mappedBy = "roles")
	private List<User> users = new ArrayList<>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "access", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "feature_id"))
	private Set<Feature> features = new TreeSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Set<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}

}
