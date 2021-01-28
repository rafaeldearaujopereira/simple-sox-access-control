package com.rafpereira.accesscontrol.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * User of a system.
 * @author rafaeldearaujopereira
 */
@Entity
@Table(name = "system_user")
public class User {

	/** Id. */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeqGen")
	@SequenceGenerator(name = "userSeqGen", allocationSize = 1, sequenceName = "user_seq")
	private Long id;

	/** Name. */
	@Column(name = "name")
	private String name;

	/** Login (the login in the organization). */
	@Column(name = "login")
	private String login;

	/** E-mail. */
	@Column(name = "email")
	private String email;

	/** The user is active (can access any system). */
	@Column(name = "active")
	private Boolean active;

	/** The manager of the user (when exists). */
	@ManyToOne
	@JoinColumn(name = "manager_id", referencedColumnName = "id")
	private User manager;
	
	/** The roles associated to the user. */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();

	@Transient
	private HashMap<String, Feature> featuresByCode = null;

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public HashMap<String, Feature> getFeaturesByCode() {
		if (this.featuresByCode == null) {
			featuresByCode = new HashMap<>();
			for (Role role : roles) {
				for (Feature feature : role.getFeatures()) {
					featuresByCode.put(feature.getCode(), feature);
				}
			}
		}
		return featuresByCode;
	}

	public void setFeaturesByCode(HashMap<String, Feature> featuresByCode) {
		this.featuresByCode = featuresByCode;
	}

}
