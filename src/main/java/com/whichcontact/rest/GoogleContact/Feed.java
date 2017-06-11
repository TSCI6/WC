package com.whichcontact.rest.GoogleContact;

import java.util.ArrayList;

public class Feed
{
  private Id id;

  public Id getId() { return this.id; }

  public void setId(Id id) { this.id = id; }

  private Title title;

  public Title getTitle() { return this.title; }

  public void setTitle(Title title) { this.title = title; }

  private OpenSearchTotalResults openSearch$totalResults;

  public OpenSearchTotalResults getOpenSearchTotalResults() { return this.openSearch$totalResults; }

  public void setOpenSearchTotalResults(OpenSearchTotalResults openSearch$totalResults) { this.openSearch$totalResults = openSearch$totalResults; }

  private OpenSearchItemsPerPage openSearch$itemsPerPage;

  public OpenSearchItemsPerPage getOpenSearchItemsPerPage() { return this.openSearch$itemsPerPage; }

  public void setOpenSearchItemsPerPage(OpenSearchItemsPerPage openSearch$itemsPerPage) { this.openSearch$itemsPerPage = openSearch$itemsPerPage; }

  private ArrayList<Entry> entry;

  public ArrayList<Entry> getEntry() { return this.entry; }

  public void setEntry(ArrayList<Entry> entry) { this.entry = entry; }
}