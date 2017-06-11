package com.whichcontact.core.service;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.whichcontact.core.entity.CrunchBaseFunding;
import com.whichcontact.core.entity.User;
import com.whichcontact.core.jpa.CrunchBaseRepository;
import com.whichcontact.rest.CrunchBase_organizations.CrunchBaseRoot;
import com.whichcontact.rest.FundingResponse.FundingRoot;
@Service
public class CrunchBaseOrganizationService {
 @Inject
 private CrunchBaseRepository crunchBaseRepo;
User user;
 ResponseDto responseDto = new ResponseDto();

public ResponseDto saveOrganization() {
  List<CrunchBaseFunding> funding = new ArrayList<>();
  RestTemplate rest = new RestTemplate();
   String organization_url = "https://api.crunchbase.com/v/3/odm-organizations?user_key=901d92226dd11d3015b7ee06aee97a3b";
   String funding_url = "https://api.crunchbase.com/v/3/funding-rounds?user_key=901d92226dd11d3015b7ee06aee97a3b";
   
  CrunchBaseRoot crunchBaseResponse = rest.getForObject(organization_url, CrunchBaseRoot.class);
  FundingRoot crunchBaseFundingResponse = rest.getForObject(funding_url, FundingRoot.class);
  for (int i = 0; i <100; i++) {
	  CrunchBaseFunding crunch = new CrunchBaseFunding();
	  crunch.setName(crunchBaseResponse.getData().getItems().get(i).getProperties().getName());
	  crunch.setCity_name(crunchBaseResponse.getData().getItems().get(i).getProperties().getCity_name());
	  crunch.setRegion_name(crunchBaseResponse.getData().getItems().get(i).getProperties().getRegion_name());
	  crunch.setCountry_code(crunchBaseResponse.getData().getItems().get(i).getProperties().getCountry_code());
	  crunch.setMoney_raised_currency_code(crunchBaseFundingResponse.getData().getItems().get(i).getProperties().getMoney_raised_currency_code());
	  crunch.setMoney_raised(crunchBaseFundingResponse.getData().getItems().get(i).getProperties().getMoney_raised());
	   
	 funding.add(crunch);	 
   
  }
  crunchBaseRepo.save(funding); 
  responseDto.setStatus(200);
  responseDto.setMessage("User added succesfully");
  return responseDto;
 }
  
public List < CrunchBaseFunding > viewFundingData() {
  return crunchBaseRepo.findAll();
 }
}