package com.whichcontact.rest.FundingResponse;

public class FundingRoot
{
    private Metadata metadata;

    private Data data;

    public void setMetadata(Metadata metadata){
        this.metadata = metadata;
    }
    public Metadata getMetadata(){
        return this.metadata;
    }
    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }
}
