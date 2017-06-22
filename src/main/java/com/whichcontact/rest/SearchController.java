package com.whichcontact.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whichcontact.core.entity.Contacts;
import com.whichcontact.core.entity.User;
import com.whichcontact.core.jpa.ContactRepository;
import com.whichcontact.core.jpa.UserLoginRepository;
import com.whichcontact.core.service.ContactService;
import com.whichcontact.core.service.InvitationNotificationService;
import com.whichcontact.core.service.ResponseDto;
import com.whichcontact.core.service.UserService;

/**
 * @author TS001127
 *
 *         the backside to the ajax call in SearchContact.js
 */

@RequestMapping("/whichcontact")
@Controller
public class SearchController {

	private static final Logger Log = Logger.getLogger(ContactController.class.getName());
	@Inject
	private ContactService contactService;
	@Inject
	private UserService userService;
	@Inject
	private UserLoginRepository userrepo;
	@Inject
	private ContactRepository contactrepo;
	@Inject
	private InvitationNotificationService invitationNotify;
	ResponseDto responseDto = new ResponseDto();

	/**
	 * @param user
	 *            user Query enterd by customer for companySearch
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * 
	 * 
	 * 
	 *             the method searchByCompany searches for all the contacts in
	 *             co
	 * 
	 * 
	 * 
	 */
	@RequestMapping(value = "/searchCo", method = RequestMethod.POST)
	@ResponseBody
	protected List<Contacts> searchByCompany(@RequestParam("user") String user, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Log.info("[In SearchController >> searchByCompany()  ...call to contactService >>SearchCompany method");
		return contactService.SearchCompany(user);

	}

	/**
	 * @param user
	 *            Query enterd by customer for nameSearch
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * 
	 * 
	 * 
	 *             the method searchByName searches for all the contacts by
	 *             their name & email-ids
	 * 
	 * 
	 * 
	 * 
	 */
	@RequestMapping(value = "/searchName", method = RequestMethod.POST)
	@ResponseBody
	protected List<Contacts> searchByName(@RequestParam("user") String user, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Contacts> resp = contactService.SearchName(user);
		resp.addAll(contactService.SearchEmail(user));
		Log.info(
				"[In SearchController >> searchByName()  ...call to contactService >>SearchName and SearchEmail method");
		return resp;

	}

	/**
	 * @param user
	 *            USERID for the contact shown
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * 
	 * 
	 * 
	 *             from SearchContact.js for finding the name and email of the
	 *             mutual contact's id specified by the parameter user
	 * 
	 * 
	 * 
	 */
	@RequestMapping(value = "/findUser", method = RequestMethod.POST)
	@ResponseBody
	public User findUser(@RequestParam("user") int user, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Log.info("[In SearchController >> findUser()  ...call to userService >>findUserById method");
		return userService.findUserById(user);

	}
	@RequestMapping(value = "/commonEmail", method = RequestMethod.POST)
	public ResponseDto userdetail() {

		Integer user_id = UserService.getUser().getId();
		List<User> userList = userrepo.findAll();
		List<String> emailList = new ArrayList<String>();

		for (int user = 0; user < userList.size(); user++) {
			String list = userList.get(user).getEmail();
			emailList.add(list);
		}

		List<Contacts> contactList = contactrepo.findByUserId(user_id);
		List<String> contactEmail = new ArrayList<String>();
		for (int i = 0; i < contactList.size(); i++) {
			String list = contactList.get(i).getEmail();
			contactEmail.add(list);

		}
		contactEmail.removeAll(emailList);

		contactService.saveEmail(contactEmail);
		responseDto.setStatus(200);
		return responseDto;
	}

	@RequestMapping(value = "/sendInvitation", method = RequestMethod.PUT)
	public ResponseDto findEmail() {
		Log.info("[In UserController >> findUser :{}, call to userService sendNotification method");
		return invitationNotify.sendNotification();
	}

}