package com.whichcontact.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author TS001127
 *
 */
@Entity
@Table(name = "contacts")
public class Contacts {

                @Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                @Column (name="contact_id")
                private Integer contactId;            
                @Column(name = "ID")
                private Integer userId;
                @Column(name = "name", unique=true, nullable=false)
                private String name;
                @Column(name = "gid")
                private String gid;
                @Column(name = "email")
                private String email;
                @Column(name = "mobile")
                private String mobile;
                @Column(name = "work")
                private String work;
                @Column(name = "phone")
                private String phone;
                @Column(name = "postal")
                private String postal;
                @Column(name = "company")
                private String company;
                @Column(name = "designation")
                private String designation;

                public String getCompany() {
                                return company;
                }

                public void setCompany(String company) {
                                this.company = company;
                }

                public String getDesignation() {
                                return designation;
                }

                public void setDesignation(String designation) {
                                this.designation = designation;
                }

                public String getPostal() {
                                return postal;
                }

                public void setPostal(String postal) {
                                this.postal = postal;
                }

                public String getName() {
                                return name;
                }

                public void setName(String name) {
                                this.name = name;
                }

                public String getGid() {
                                return gid;
                }

                public void setGid(String gid) {
                                this.gid = gid;
                }

                public String getEmail() {
                                return email;
                }

                public void setEmail(String email) {
                                this.email = email;
                }

                public String getMobile() {
                                return mobile;
                }

                public void setMobile(String mobile) {
                                this.mobile = mobile;
                }

                public String getWork() {
                                return work;
                }

                public void setWork(String work) {
                                this.work = work;
                }

                public String getPhone() {
                                return phone;
                }

                public void setPhone(String phone) {
                                this.phone = phone;
                }

                public Integer getContactId() {
                                return contactId;
                }

                public void setContactId(Integer contactId) {
                                this.contactId = contactId;
                }

                public Integer getUserId() {
                                return userId;
                }

                public void setUserId(Integer userId) {
                                this.userId = userId;
                }


 
}
