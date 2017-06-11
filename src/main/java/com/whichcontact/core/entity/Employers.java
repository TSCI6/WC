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
 /**
  * @return
  */
 public int getId() {
   return id;
  }
  /**
   * @param id
   */
 public void setId(int id) {
   this.id = id;
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
 public String getWebsite() {
   return website;
  }
  /**
   * @param website
   */
 public void setWebsite(String website) {
   this.website = website;
  }
  /**
   * @return
   */
 public String getLogo() {
   return logo;
  }
  /**
   * @param logo
   */
 public void setLogo(String logo) {
  this.logo = logo;
 }
}