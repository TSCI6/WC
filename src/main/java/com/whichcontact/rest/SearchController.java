package com.whichcontact.rest;

import java.io.IOException;
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
import com.whichcontact.core.service.ContactService;
import com.whichcontact.core.service.UserService;

/**
 * @author TS001127
 *
 */

@RequestMapping("/whichcontact")
@Controller
public class SearchController {
	/*
	 * the backside to the ajax call in SearchContact.js
	 * 
	 * 
	 */
	private static final Logger Log = Logger.getLogger(ContactController.class.getName());
	@Inject
	private ContactService contactService;
	@Inject
	private UserService userService;


	/**
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * 
	 * 
	 * 
	 * the method searchByCompany searches for all the contacts working in the
	 * company specified by the parameter user
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
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * 
	 * 
	 * 
	 * the method searchByName searches for all the contacts by their name
	 * specified by the parameter user also searches the contacts by name in the
	 * email-ids
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
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * 
	 * 
	 * 
	 * from SearchContact.js for finding the name and email of the mutual
	 * contact's id specified by the parameter user
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
	/*
	 * @RequestMapping(value = "/viewco", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public List < Contacts > userdetail() {
	 * System.out.println(contactService.viewContact()); //new
	 * Gson().toJson(contactService.viewContact()); List < Contacts > response =
	 * contactService.viewContact(); return response; }
	 */

}