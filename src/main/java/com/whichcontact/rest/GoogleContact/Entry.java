package com.whichcontact.rest.GoogleContact;

import java.util.ArrayList;

/**
 * @author TS001127
 *
 */
public class Entry
{
  private Id2 id;

  public Id2 getId() { return this.id; }

  public void setId(Id2 id) { this.id = id; }

  private Title2 title;

  public Title2 getTitle() { return this.title; }

  public void setTitle(Title2 title) { this.title = title; }

  private ArrayList<GdEmail> gd$email;

  public ArrayList<GdEmail> getGdEmail() { return this.gd$email; }

  public void setGdEmail(ArrayList<GdEmail> gd$email) { this.gd$email = gd$email; }

  private ArrayList<GdPhoneNumber> gd$phoneNumber;

  public ArrayList<GdPhoneNumber> getGdPhoneNumber() { return this.gd$phoneNumber; }

  public void setGdPhoneNumber(ArrayList<GdPhoneNumber> gd$phoneNumber) { this.gd$phoneNumber = gd$phoneNumber; }

  private ArrayList<GdPostalAddress> gd$postalAddress;

  public ArrayList<GdPostalAddress> getGdPostalAddress() { return this.gd$postalAddress; }

  public void setGdPostalAddress(ArrayList<GdPostalAddress> gd$postalAddress) { this.gd$postalAddress = gd$postalAddress; }
}

