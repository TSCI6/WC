package com.whichcontact.rest.glassdoor;

public class FeaturedReview
{
  private String attributionURL;

  public String getAttributionURL() { return this.attributionURL; }

  public void setAttributionURL(String attributionURL) { this.attributionURL = attributionURL; }

  private int id;

  public int getId() { return this.id; }

  public void setId(int id) { this.id = id; }

  private boolean currentJob;

  public boolean getCurrentJob() { return this.currentJob; }

  public void setCurrentJob(boolean currentJob) { this.currentJob = currentJob; }

  private String reviewDateTime;

  public String getReviewDateTime() { return this.reviewDateTime; }

  public void setReviewDateTime(String reviewDateTime) { this.reviewDateTime = reviewDateTime; }

  private String jobTitle;

  public String getJobTitle() { return this.jobTitle; }

  public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

  private String location;

  public String getLocation() { return this.location; }

  public void setLocation(String location) { this.location = location; }

  private String headline;

  public String getHeadline() { return this.headline; }

  public void setHeadline(String headline) { this.headline = headline; }

  private String pros;

  public String getPros() { return this.pros; }

  public void setPros(String pros) { this.pros = pros; }

  private String cons;

  public String getCons() { return this.cons; }

  public void setCons(String cons) { this.cons = cons; }

  private int overall;

  public int getOverall() { return this.overall; }

  public void setOverall(int overall) { this.overall = overall; }

  private int overallNumeric;

  public int getOverallNumeric() { return this.overallNumeric; }

  public void setOverallNumeric(int overallNumeric) { this.overallNumeric = overallNumeric; }

  private String jobTitleFromDb;

  public String getJobTitleFromDb() { return this.jobTitleFromDb; }

  public void setJobTitleFromDb(String jobTitleFromDb) { this.jobTitleFromDb = jobTitleFromDb; }
}