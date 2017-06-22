

package com.whichcontact.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author TS001127
 *
 */
@Entity
@Table(name = "digitalindia")

public class DigitalIndia implements Serializable {

 private static final long serialVersionUID = 1L;
 @Id
 @Column(name = "cin")
 private String cin;
 @Column(name = "name")
 private String name;
 @Column(name = "investment")
 private String investment;
 @Column(name = "state")
 private String state;
 @Column(name = "businessactivity")
 private String businessactivity;
 @Column(name = "REGISTEREDADDRESS")
 private String REGISTEREDADDRESS;
 @Column(name = "LASTREVENUE")
 private String LASTREVENUE;
public String getREGISTEREDADDRESS() {
	return REGISTEREDADDRESS;
}
public void setREGISTEREDADDRESS(String rEGISTEREDADDRESS) {
	this.REGISTEREDADDRESS = rEGISTEREDADDRESS;
}
public String getLASTREVENUE() {
	return LASTREVENUE;
}
public void setLASTREVENUE(String lASTREVENUE) {
	this.LASTREVENUE = lASTREVENUE;
}
public String getCin() {
	return cin;
}
public void setCin(String cin) {
	this.cin = cin;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getInvestment() {
	return investment;
}
public void setInvestment(String investment) {
	this.investment = investment;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getBusinessactivity() {
	return businessactivity;
}
public void setBusinessactivity(String businessactivity) {
	this.businessactivity = businessactivity;
}
 
}