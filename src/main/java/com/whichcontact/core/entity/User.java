package com.whichcontact.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
  @Column(name = "name")
private String name;
@Column(name = "email")
private String email;
@Column(name = "password")
private String password;
@Column(name = "company")
private String company;
@Column(name = "designation")
private String designation;
@Column(name = "image")
private String image;



public String getImage() {
                return image;
}
public void setImage(String image) {
                this.image = image;
}
/**
   * @return
   */
public String getName() {
   return name;
  }
  /**
   * @param name
   */
public void setName(String name) {
   this.name = name;
  }
  /**
   * @return
   */
public String getEmail() {
   return email;
  }
  /**
   * @param email
   */
public void setEmail(String email) {
   this.email = email;
  }
  /**
   * @return
   */
public String getPassword() {
   return password;
  }
  /**
   * @param password
   */
public void setPassword(String password) {
   this.password = password;
  }
  /**
   * @return
   */
public String getCompany() {
   return company;
  }
  /**
   * @param company
   */
public void setCompany(String company) {
   this.company = company;
  }
  /**
   * @return
   */
public String getDesignation() {
   return designation;
  }
  /**
   * @param designation
   */
public void setDesignation(String designation) {
  this.designation = designation;
}
public Integer getId() {
                return id;
}
public void setId(Integer id) {
                this.id = id;
}

 
}
