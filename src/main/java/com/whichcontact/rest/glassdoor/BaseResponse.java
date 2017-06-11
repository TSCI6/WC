package com.whichcontact.rest.glassdoor;

public class BaseResponse {

	private boolean success;

	  public boolean getSuccess() { return this.success; }

	  public void setSuccess(boolean success) { this.success = success; }

	  private String status;

	  public String getStatus() { return this.status; }

	  public void setStatus(String status) { this.status = status; }

	  private String jsessionid;

	  public String getJsessionid() { return this.jsessionid; }

	  public void setJsessionid(String jsessionid) { this.jsessionid = jsessionid; }

	  private Response response;

	  public Response getResponse() { return this.response; }

	  public void setResponse(Response response) { this.response = response; }
}
