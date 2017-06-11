package com.whichcontact.rest.glassdoor;

public class Ceo
{
  private String name;

  public String getName() { return this.name; }

  public void setName(String name) { this.name = name; }

  private String title;

  public String getTitle() { return this.title; }

  public void setTitle(String title) { this.title = title; }

  private int numberOfRatings;

  public int getNumberOfRatings() { return this.numberOfRatings; }

  public void setNumberOfRatings(int numberOfRatings) { this.numberOfRatings = numberOfRatings; }

  private int pctApprove;

  public int getPctApprove() { return this.pctApprove; }

  public void setPctApprove(int pctApprove) { this.pctApprove = pctApprove; }

  private int pctDisapprove;

  public int getPctDisapprove() { return this.pctDisapprove; }

  public void setPctDisapprove(int pctDisapprove) { this.pctDisapprove = pctDisapprove; }
}