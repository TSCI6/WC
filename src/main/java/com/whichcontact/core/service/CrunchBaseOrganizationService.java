package com.whichcontact.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.whichcontact.core.entity.CrunchBaseFunding;
import com.whichcontact.core.entity.User;
import com.whichcontact.core.jpa.CrunchBaseRepository;
import com.whichcontact.rest.CrunchBase_organizations.CrunchBaseRoot;
import com.whichcontact.rest.FundingResponse.FundingRoot;

/**
 * @author AS001136 This class is Hitting the CrunchBase API to retrieve The TOP
 *         funding companies data
 */
@Service
public class CrunchBaseOrganizationService {
	@Inject
	private CrunchBaseRepository crunchBaseRepo;

	@Value("${organization_url}")
	private String org_url;
	@Value("${funding_url}")
	private String fund_url;
	@Value("${user_key}")
	private String user_key;
	User user;
	ResponseDto responseDto = new ResponseDto();

	/**
	 * @return ResponseDto is Returning the response as API is hitting
	 *         successfully or not
	 */
	public ResponseDto saveOrganization() {
		List<CrunchBaseFunding> funding = new ArrayList<>();
		String organization_url = org_url + user_key;
		String funding_url = fund_url + user_key;
		RestTemplate rest = new RestTemplate();
		CrunchBaseRoot crunchBaseResponse = rest.getForObject(organization_url, CrunchBaseRoot.class);
		/**
		 * Hitting the CrunchBase API
		 */
		FundingRoot crunchBaseFundingResponse = rest.getForObject(funding_url, FundingRoot.class);
		for (int index = 0; index < 100; index++) {
			CrunchBaseFunding crunch = new CrunchBaseFunding();
			crunch.setName(crunchBaseResponse.getData().getItems().get(index).getProperties().getName());
			crunch.setCity_name(crunchBaseResponse.getData().getItems().get(index).getProperties().getCity_name());
			crunch.setRegion_name(crunchBaseResponse.getData().getItems().get(index).getProperties().getRegion_name());
			crunch.setCountry_code(
					crunchBaseResponse.getData().getItems().get(index).getProperties().getCountry_code());
			crunch.setMoney_raised_currency_code(crunchBaseFundingResponse.getData().getItems().get(index)
					.getProperties().getMoney_raised_currency_code());
			crunch.setMoney_raised(
					crunchBaseFundingResponse.getData().getItems().get(index).getProperties().getMoney_raised());

			funding.add(crunch);

		}
		/**
		 * Saving funding data in database
		 */
		crunchBaseRepo.save(funding);
		responseDto.setStatus(200);
		responseDto.setMessage("Funding_Response table update sucessfully");
		return responseDto;
	}

	public List<CrunchBaseFunding> viewFundingData() {
		return crunchBaseRepo.findAll();
	}
}
