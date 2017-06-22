package com.whichcontact.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author AC001133
 *
 */
@Entity
@Table(name = "organization_data")

public class Employers implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "company_id")
	private int id;
	
	@Column(name = "company_name")
	private String name;
	
	@Column(name = "website")
	private String website;
	
	@Column(name = "logo")
	private String logo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

}