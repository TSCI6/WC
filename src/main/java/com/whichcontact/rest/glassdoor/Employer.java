package com.whichcontact.rest.glassdoor;

public class Employer
{
  private int id;

  public int getId() { return this.id; }

  public void setId(int id) { this.id = id; }

  private String name;

  public String getName() { return this.name; }

  public void setName(String name) { this.name = name; }

  private String website;

  public String getWebsite() { return this.website; }

  public void setWebsite(String website) { this.website = website; }

  private boolean isEEP;

  public boolean getIsEEP() { return this.isEEP; }

  public void setIsEEP(boolean isEEP) { this.isEEP = isEEP; }

  private boolean exactMatch;

  public boolean getExactMatch() { return this.exactMatch; }

  public void setExactMatch(boolean exactMatch) { this.exactMatch = exactMatch; }

  private String industry;

  public String getIndustry() { return this.industry; }

  public void setIndustry(String industry) { this.industry = industry; }

  private int numberOfRatings;

  public int getNumberOfRatings() { return this.numberOfRatings; }

  public void setNumberOfRatings(int numberOfRatings) { this.numberOfRatings = numberOfRatings; }

  private String squareLogo;

  public String getSquareLogo() { return this.squareLogo; }

  public void setSquareLogo(String squareLogo) { this.squareLogo = squareLogo; }

  private String overallRating;

  public String getOverallRating() { return this.overallRating; }

  public void setOverallRating(String overallRating) { this.overallRating = overallRating; }

  private String ratingDescription;

  public String getRatingDescription() { return this.ratingDescription; }

  public void setRatingDescription(String ratingDescription) { this.ratingDescription = ratingDescription; }

  private String cultureAndValuesRating;

  public String getCultureAndValuesRating() { return this.cultureAndValuesRating; }

  public void setCultureAndValuesRating(String cultureAndValuesRating) { this.cultureAndValuesRating = cultureAndValuesRating; }

  private String seniorLeadershipRating;

  public String getSeniorLeadershipRating() { return this.seniorLeadershipRating; }

  public void setSeniorLeadershipRating(String seniorLeadershipRating) { this.seniorLeadershipRating = seniorLeadershipRating; }

  private String compensationAndBenefitsRating;

  public String getCompensationAndBenefitsRating() { return this.compensationAndBenefitsRating; }

  public void setCompensationAndBenefitsRating(String compensationAndBenefitsRating) { this.compensationAndBenefitsRating = compensationAndBenefitsRating; }

  private String careerOpportunitiesRating;

  public String getCareerOpportunitiesRating() { return this.careerOpportunitiesRating; }

  public void setCareerOpportunitiesRating(String careerOpportunitiesRating) { this.careerOpportunitiesRating = careerOpportunitiesRating; }

  private String workLifeBalanceRating;

  public String getWorkLifeBalanceRating() { return this.workLifeBalanceRating; }

  public void setWorkLifeBalanceRating(String workLifeBalanceRating) { this.workLifeBalanceRating = workLifeBalanceRating; }

  private int recommendToFriendRating;

  public int getRecommendToFriendRating() { return this.recommendToFriendRating; }

  public void setRecommendToFriendRating(int recommendToFriendRating) { this.recommendToFriendRating = recommendToFriendRating; }

  private FeaturedReview featuredReview;

  public FeaturedReview getFeaturedReview() { return this.featuredReview; }

  public void setFeaturedReview(FeaturedReview featuredReview) { this.featuredReview = featuredReview; }

  private Integer sectorId;

  public Integer getSectorId() { return this.sectorId; }

  public void setSectorId(Integer sectorId) { this.sectorId = sectorId; }

  private String sectorName;

  public String getSectorName() { return this.sectorName; }

  public void setSectorName(String sectorName) { this.sectorName = sectorName; }

  private Integer industryId;

  public Integer getIndustryId() { return this.industryId; }

  public void setIndustryId(Integer industryId) { this.industryId = industryId; }

  private String industryName;

  public String getIndustryName() { return this.industryName; }

  public void setIndustryName(String industryName) { this.industryName = industryName; }

  private Ceo ceo;

  public Ceo getCeo() { return this.ceo; }

  public void setCeo(Ceo ceo) { this.ceo = ceo; }
}