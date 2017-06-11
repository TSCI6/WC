package com.whichcontact.core.batch;

import static java.util.Objects.nonNull;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.whichcontact.core.entity.Employers;
import com.whichcontact.core.entity.User;
import com.whichcontact.core.jpa.EmployersRepository;
import com.whichcontact.core.service.ResponseDto;
import com.whichcontact.core.service.UserService;
import com.whichcontact.rest.glassdoor.BaseResponse;

@Component
public class ScheduledTasks {

	@Inject
	private EmployersRepository employerRepository;
	User user;
	ResponseDto responseDto = new ResponseDto();
	String domain;
	int page_No=1;

	/**
	 * @return ResponseDto
	 * @throws InterruptedException
	 */
	@Scheduled(fixedRate = 100000)
	public ResponseDto saveOrganization() throws InterruptedException {
		ResponseDto responseDto = new ResponseDto();
		if (nonNull(UserService.getUser())) {
			try {
				new UserService();
				domain = UserService.getUser().getDesignation();
			} catch (Exception e) {
				System.out.println(e);
			}
			if (domain != null) {

				Employers employers = new Employers();
				RestTemplate rest = new RestTemplate();
				 
				String url = "http://api.glassdoor.com/api/api.htm?t.p=";
				String partner_id = "144125";
				String partner_key = "dQBVKkYQ905";
				String compleate_url = url + partner_id + "&t.k=" + partner_key + "&pn=" + page_No
						+ "&userip=0.0.0.0&useragent=&format=json&v=1&action=employers&q=" + domain;
				
				BaseResponse glassdoorResponse = rest.getForObject(compleate_url, BaseResponse.class);
				for (int i = 0; i < 9; i++) {
					
					employers.setName(glassdoorResponse.getResponse().getEmployers().get(i).getName());
					employers.setLogo(glassdoorResponse.getResponse().getEmployers().get(i).getSquareLogo());
					employers.setWebsite(glassdoorResponse.getResponse().getEmployers().get(i).getWebsite());
					employerRepository.save(employers);

				}
				page_No ++;
				responseDto.setStatus(200);
				responseDto.setMessage("User added succesfully");
				System.out.println("organization data table updated");
				 
			}

		}
		
		return responseDto;
	}
	
}
