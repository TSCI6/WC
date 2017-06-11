package com.whichcontact.rest.FundingResponse;

public class Paging
{
    private int total_items;

    private int number_of_pages;

    private int current_page;

    private String sort_order;

    private int items_per_page;

    private String next_page_url;

    private String prev_page_url;

    public void setTotal_items(int total_items){
        this.total_items = total_items;
    }
    public int getTotal_items(){
        return this.total_items;
    }
    public void setNumber_of_pages(int number_of_pages){
        this.number_of_pages = number_of_pages;
    }
    public int getNumber_of_pages(){
        return this.number_of_pages;
    }
    public void setCurrent_page(int current_page){
        this.current_page = current_page;
    }
    public int getCurrent_page(){
        return this.current_page;
    }
    public void setSort_order(String sort_order){
        this.sort_order = sort_order;
    }
    public String getSort_order(){
        return this.sort_order;
    }
    public void setItems_per_page(int items_per_page){
        this.items_per_page = items_per_page;
    }
    public int getItems_per_page(){
        return this.items_per_page;
    }
    public void setNext_page_url(String next_page_url){
        this.next_page_url = next_page_url;
    }
    public String getNext_page_url(){
        return this.next_page_url;
    }
    public void setPrev_page_url(String prev_page_url){
        this.prev_page_url = prev_page_url;
    }
    public String getPrev_page_url(){
        return this.prev_page_url;
    }
}