package com.whichcontact.rest.CrunchBase_organizations;

public class Metadata
{
    private int version;

    private String www_path_prefix;

    private String api_path_prefix;

    private String image_path_prefix;

    public void setVersion(int version){
        this.version = version;
    }
    public int getVersion(){
        return this.version;
    }
    public void setWww_path_prefix(String www_path_prefix){
        this.www_path_prefix = www_path_prefix;
    }
    public String getWww_path_prefix(){
        return this.www_path_prefix;
    }
    public void setApi_path_prefix(String api_path_prefix){
        this.api_path_prefix = api_path_prefix;
    }
    public String getApi_path_prefix(){
        return this.api_path_prefix;
    }
    public void setImage_path_prefix(String image_path_prefix){
        this.image_path_prefix = image_path_prefix;
    }
    public String getImage_path_prefix(){
        return this.image_path_prefix;
    }
}