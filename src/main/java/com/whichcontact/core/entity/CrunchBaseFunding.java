package com.whichcontact.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Funding_Response")
public class CrunchBaseFunding implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name")
	private String name;
	
	@Column(name = "city_name")
	private String city_name;
	
	@Column(name = "region_name")
	private String region_name;
	
	@Column(name = "country_code")
	private String country_code;

	@Column(name = "money_raised_currency_code")
	private String money_raised_currency_code;
	
	@Column(name = "money_raised")
	private String money_raised;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getRegion_name() {
		return region_name;
	}

	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getMoney_raised_currency_code() {
		return money_raised_currency_code;
	}

	public void setMoney_raised_currency_code(String money_raised_currency_code) {
		this.money_raised_currency_code = money_raised_currency_code;
	}

	public String getMoney_raised() {
		return money_raised;
	}

	public void setMoney_raised(String money_raised) {
		this.money_raised = money_raised;
	}

}