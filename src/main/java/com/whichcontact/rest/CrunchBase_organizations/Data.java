package com.whichcontact.rest.CrunchBase_organizations;
import java.util.List;
public class Data
{
    private Paging paging;

    private List<Items> items;

    public void setPaging(Paging paging){
        this.paging = paging;
    }
    public Paging getPaging(){
        return this.paging;
    }
    public void setItems(List<Items> items){
        this.items = items;
    }
    public List<Items> getItems(){
        return this.items;
    }
}
