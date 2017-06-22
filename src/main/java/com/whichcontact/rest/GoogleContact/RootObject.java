package com.whichcontact.rest.GoogleContact;


/**
 * @author TS001127
 *
 */
public class RootObject
{
  private String version;

  public String getVersion() { return this.version; }

  public void setVersion(String version) { this.version = version; }

  private String encoding;

  public String getEncoding() { return this.encoding; }

  public void setEncoding(String encoding) { this.encoding = encoding; }

  private Feed feed;

  public Feed getFeed() { return this.feed; }

  public void setFeed(Feed feed) { this.feed = feed; }
}
