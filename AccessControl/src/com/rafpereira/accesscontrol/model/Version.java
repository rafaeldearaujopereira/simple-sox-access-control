package com.rafpereira.accesscontrol.model;

import java.math.BigDecimal;
import java.util.Date;

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
 * The version of a feature (usually used for systems).
 * 
 * @author rafaeldearaujopereira
 */
@Entity
@Table(name = "version")
public class Version {

	/** Id. */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "versionSeqGen")
	@SequenceGenerator(name = "versionSeqGen", allocationSize = 1, sequenceName = "version_seq")
	private Long id;

	/** The feature. */
	@ManyToOne
	@JoinColumn(name = "feature_id", referencedColumnName = "id")
	private Feature feature;

	/** Name. */
	@Column(name = "name")
	private String name;

	/** Description. */
	@Column(name = "description")
	private String description;

	/** Date. */
	@Column(name = "release_date")
	private Date releaseDate;

	/** Mandatory. */
	@Column(name = "mandatory")
	private Boolean mandatory;

	/** Version number. */
	@Column(name = "version_number")
	private BigDecimal versionNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
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

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
	}

	public BigDecimal getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(BigDecimal versionNumber) {
		this.versionNumber = versionNumber;
	}

}
