package com.whichcontact.core.batch;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.whichcontact.core.entity.DigitalIndia;
import com.whichcontact.core.entity.Employers;
import com.whichcontact.core.jpa.DigitalIndiaRepository;
import com.whichcontact.core.jpa.EmployersRepository;
import com.whichcontact.core.service.ResponseDto;
import com.whichcontact.core.service.UserService;
import com.whichcontact.rest.glassdoor.BaseResponse;

import  com.whichcontact.rest.DigitalIndia.MainResponse;

/**
 *  The ScheduledTasks Class hits GlassdoorApi in every 3 second
 *         and simultaneously stored data in database.
 *         
 *  The ScheduledTasks Class hits GlassdoorApi in every 3 second
 *         and simultaneously stored data in database.
 */
@Component
@PropertySource({ "classpath:which-contact.properties" })
public class ScheduledTasks {
	@Inject
	private EmployersRepository employerRepository;
	@Inject
	private DigitalIndiaRepository digitalIndRepository;
	
	@Value("${glassdoor-base-url}")
	private String BASE_URL;
	@Value("${glassdoor-partnerId}")
	private String partner_id;
	@Value("${glassdoor-partnerKey}")
	private String partner_key;
	@Value("${Size.userForm.username}")
	private String usernNameSizeMessage;
	@Value("${mca_url_prefix}")
	private String urlPrefix;
	@Value("${mca_url_suffix}")
	private String urlSuffix;
	ResponseDto responseDto = new ResponseDto();
	private String domain;
	private Integer page_No = 1;
	private Integer state = 0;
	private Integer Count;
	private String[] stateResourceId = 
			{"ccd42a4e-b657-4244-a43f-a203e3cf7dd8","3f328009-8f64-426d-9228-750a3fe8e326", "071758ef-8b2b-4ff6-8774-bcf782214779", //bengal ,bihar,telangana
			"74a2d302-e24f-42cf-b95c-ff279bcf133d", "f8547c08-a7bf-4e85-b179-c57b5bd135a8", "a1513fa4-007e-4085-a367-7a65562e9bf4" ,//uk,up,tripura
			"3bac7cea-66b4-49b0-b310-4cd730e28287", "7502bd54-2f04-43a5-ae40-437628b0145a", "997ad190-4308-4d8e-808c-8148c2c9ed08", //punjab , delhi,odisha
			"133dd8f2-44b3-4a6d-a208-72b1030c51fb", "73d8110b-4492-48b5-9f8b-b5bf2ce65261", "d1ac29db-549d-44b2-9bea-28d6e449ff85", //rajasthan,tamil,maharashtra
			"6a48e198-1b5c-46e6-ad9e-789b231992c1", "006e6aff-6108-4bb6-ba60-ecd9b83a5280", "df73b4ed-2355-4f2e-9392-4b3201bde8b3",//assam , andhra, chandigarh
			"f526be27-c0bf-4d99-b931-0f8e247e59d0","1ea03789-3147-4a39-a85e-22f4ca128689", "fc0730f1-9736-409d-b3d8-0ac64122c225",//madhyapradesh,gujarat,haryana
			"da1e82e7-fb09-48b3-96cb-8fd0411d4ee6", "f8dd5590-8843-49be-9ae2-79c5b3e23ed0", "b4eb9d9b-c8e7-4ec3-b564-e6a018f7249e" ,//himachal jammu,jharkhand
			"080e668f-1e57-4376-8269-b41ca9c39cc6", "071aa695-4a6e-4bb9-a109-6e9da1329967", "4081f64b-6702-46de-b380-d73edf1ca395",//karnataka,kerala,chattisgarh
			"4dbe5667-7b6b-41d7-82af-211562424d9a", "6a6e802c-66e2-47c2-ad20-4abc9289c85b", "76fdab68-795b-42f4-bc6d-188442b3ff57",//goa,nagaland,mizoram
			"f4a928ea-757e-462c-957e-f783f6cfc206","44486d32-3c20-41f4-9376-9f4ac360eaa1","57ae016f-b67f-42dc-b473-2fdae3621f3b"};//puducherry , manipur ,meghalaya


	private static final Logger log = Logger.getLogger(ScheduledTasks.class.getName());

       /**
       * @return responseDto 
       * This method return a responseDto status whether it is
       *         a success or not.
       */
       @Scheduled(fixedRate = 100000000)
       public ResponseDto saveOrganization() {
              if (nonNull(UserService.getUser())) {
                     domain = UserService.getUser().getDesignation();
              }
              if (domain != null) {
                     Employers employers = new Employers();
                     RestTemplate rest = new RestTemplate();
                     String complete_url = BASE_URL + partner_id + "&t.k=" + partner_key + "&pn=" + page_No
                                  + "&userip=0.0.0.0&useragent=&format=json&v=1&action=employers&q=" + domain;
                     BaseResponse glassdoorResponse = rest.getForObject(complete_url, BaseResponse.class);
                     /**
                      * Hitting the GlassDoor API and saving the response in Employers entity.
                      */
                     for (Count = 0; Count < 9; Count++) {
                            employers.setName(glassdoorResponse.getResponse().getEmployers().get(Count).getName());
                            employers.setLogo(glassdoorResponse.getResponse().getEmployers().get(Count).getSquareLogo());
                            employers.setWebsite(glassdoorResponse.getResponse().getEmployers().get(Count).getWebsite());
                           employerRepository.save(employers);
                     }
                     page_No++;
                     responseDto.setStatus(200);
                     responseDto.setMessage("User added succesfully");
                     log.info("Organization Table Updated");
              }

              return responseDto;
       }
       
       
       	ArrayList<String> resourceId = new ArrayList<String>(Arrays.asList(stateResourceId));

	/**
	 * @return
	 * @throws InterruptedException
	 * 
	 *             scheduler to fetch indian companies from api statewise
	 */
	@Scheduled(fixedRate = 100000000)
	public ResponseDto saveIndianCos() throws InterruptedException {
		ResponseDto responseDto = new ResponseDto();

		DigitalIndia digitalIndCos = new DigitalIndia();
		RestTemplate rest = new RestTemplate();
		if (state < 30) {

			String completeUrl = urlPrefix + resourceId.get(state) + urlSuffix;
			MainResponse response;
			response = rest.getForObject(completeUrl, MainResponse.class);

			for (int index = 1; index <= 98; index++) {

				digitalIndCos.setCin(response.records[index].getCORPORATEIDENTIFICATIONNUMBER());
				digitalIndCos.setName(response.records[index].getCOMPANYNAME());
				digitalIndCos.setState(response.records[index].getREGISTEREDSTATE());
				digitalIndCos.setBusinessactivity("service");
				digitalIndCos.setInvestment(response.records[index].getAUTHORIZEDCAPITAL());
				digitalIndCos.setLASTREVENUE(response.records[index].getPAIDUPCAPITAL());
				digitalIndCos.setREGISTEREDADDRESS(response.records[index].getREGISTEREDOFFICEADDRESS());

				digitalIndRepository.save(digitalIndCos);

			}
			state++;
			log.info("india state" + state + " table updated");
		} else {
		}
		responseDto.setStatus(200);
		responseDto.setMessage(usernNameSizeMessage);

		return responseDto;
	}

}
