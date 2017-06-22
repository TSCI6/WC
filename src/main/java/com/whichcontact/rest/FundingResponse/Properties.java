package com.whichcontact.rest.FundingResponse;

 

public class Properties
{
    private String permalink;

    private String api_path;

    private String web_path;

    private String funding_type;

    private String series;

    private String series_qualifier;

    

 
 

    private int closed_on_trust_code;

    private String money_raised;

    private String money_raised_currency_code;

    private String money_raised_usd;

    private String target_money_raised;

    private String target_money_raised_currency_code;

    private String target_money_raised_usd;

    private int created_at;

    private int updated_at;

    public void setPermalink(String permalink){
        this.permalink = permalink;
    }
    public String getPermalink(){
        return this.permalink;
    }
    public void setApi_path(String api_path){
        this.api_path = api_path;
    }
    public String getApi_path(){
        return this.api_path;
    }
    public void setWeb_path(String web_path){
        this.web_path = web_path;
    }
    public String getWeb_path(){
        return this.web_path;
    }
    public void setFunding_type(String funding_type){
        this.funding_type = funding_type;
    }
    public String getFunding_type(){
        return this.funding_type;
    }
    public void setSeries(String series){
        this.series = series;
    }
    public String getSeries(){
        return this.series;
    }
    public void setSeries_qualifier(String series_qualifier){
        this.series_qualifier = series_qualifier;
    }
    public String getSeries_qualifier(){
        return this.series_qualifier;
    }
    
    public void setClosed_on_trust_code(int closed_on_trust_code){
        this.closed_on_trust_code = closed_on_trust_code;
    }
    public int getClosed_on_trust_code(){
        return this.closed_on_trust_code;
    }
    public void setMoney_raised(String money_raised){
        this.money_raised = money_raised;
    }
    public String getMoney_raised(){
        return this.money_raised;
    }
    public void setMoney_raised_currency_code(String money_raised_currency_code){
        this.money_raised_currency_code = money_raised_currency_code;
    }
    public String getMoney_raised_currency_code(){
        return this.money_raised_currency_code;
    }
    public void setMoney_raised_usd(String money_raised_usd){
        this.money_raised_usd = money_raised_usd;
    }
    public String getMoney_raised_usd(){
        return this.money_raised_usd;
    }
    public void setTarget_money_raised(String target_money_raised){
        this.target_money_raised = target_money_raised;
    }
    public String getTarget_money_raised(){
        return this.target_money_raised;
    }
    public void setTarget_money_raised_currency_code(String target_money_raised_currency_code){
        this.target_money_raised_currency_code = target_money_raised_currency_code;
    }
    public String getTarget_money_raised_currency_code(){
        return this.target_money_raised_currency_code;
    }
    public void setTarget_money_raised_usd(String target_money_raised_usd){
        this.target_money_raised_usd = target_money_raised_usd;
    }
    public String getTarget_money_raised_usd(){
        return this.target_money_raised_usd;
    }
    public void setCreated_at(int created_at){
        this.created_at = created_at;
    }
    public int getCreated_at(){
        return this.created_at;
    }
    public void setUpdated_at(int updated_at){
        this.updated_at = updated_at;
    }
    public int getUpdated_at(){
        return this.updated_at;
    }
}