package com.whichcontact.rest.glassdoor;

import java.util.ArrayList;

public class Response
{
  private String attributionURL;

  public String getAttributionURL() { return this.attributionURL; }

  public void setAttributionURL(String attributionURL) { this.attributionURL = attributionURL; }

  private int currentPageNumber;

  public int getCurrentPageNumber() { return this.currentPageNumber; }

  public void setCurrentPageNumber(int currentPageNumber) { this.currentPageNumber = currentPageNumber; }

  private int totalNumberOfPages;

  public int getTotalNumberOfPages() { return this.totalNumberOfPages; }

  public void setTotalNumberOfPages(int totalNumberOfPages) { this.totalNumberOfPages = totalNumberOfPages; }

  private int totalRecordCount;

  public int getTotalRecordCount() { return this.totalRecordCount; }

  public void setTotalRecordCount(int totalRecordCount) { this.totalRecordCount = totalRecordCount; }

  private ArrayList<Employer> employers;

  public ArrayList<Employer> getEmployers() { return this.employers; }

  public void setEmployers(ArrayList<Employer> employers) { this.employers = employers; }
}