package com.whichcontact.rest;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.whichcontact.core.entity.User;
import com.whichcontact.core.service.NotificationService;
import com.whichcontact.core.service.ResetPasswordDto;
import com.whichcontact.core.service.ResponseDto;
import com.whichcontact.core.service.UserService;

@RequestMapping("/whichcontact")
@RestController
public class UserController {

	private static final Logger Log = Logger.getLogger(UserController.class.getName());
	@Inject
	private UserService userService;
	User user;
	@Inject
	private NotificationService notificationService;

	/**
	 * @param username
	 *            getting the username from ui
	 * @param password
	 *            getting the password from ui
	 * @return the status of success or failure
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseDto checkUser(@RequestParam(value = "username") final String username,
			@RequestParam(value = "password") final String password) {
		Log.info("[In UserController >> checkUser :{}, call to userService authorizeUser method");

		return userService.authorizeUser(username, password);
	}

	/**
	 * The saveUser method is use for save the Value of User During The
	 * Registration
	 * 
	 * @param username
	 *            Getting Username from user
	 * @param mailId
	 *            Getting mailId from user
	 * @param password
	 *            Getting password from user
	 * @param company
	 *            Getting company from user
	 * @param designation
	 *            Getting Designation from user
	 * @return the status of success or failure
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResponseDto RegisterUser(@RequestParam(value = "username") final String username,
			@RequestParam(value = "mailId") final String mailId,
			@RequestParam(value = "password") final String password,
			@RequestParam(value = "company") final String company,
			@RequestParam(value = "designation") final String designation) {
		Log.info("[In UserController >> RegisterUser :{}, call to userService saveUser method");
		return userService.saveUser(username, mailId, password, company, designation);
	}

	/**
	 * The userProfile method is use for edit the UserProfile
	 * 
	 * @param mailid
	 *            Getting new MailId from user
	 * @param password
	 *            Getting new password from user
	 * @param company
	 *            Getting new Company from User
	 * @param designation
	 *            Getting new Designtaion from User
	 * @param image
	 *            Getting user Profile picture
	 * @return the status of success or failure
	 * @throws IOException
	 */
	@RequestMapping(value = "/editUserProfile", method = RequestMethod.POST)
	public ResponseDto userProfile(MultipartHttpServletRequest request, HttpSession session) throws IOException

	{
		String mailId = request.getParameter("editMailId");
		String password = request.getParameter("password");
		String company = request.getParameter("editCompany");
		String designation = request.getParameter("editDesignation");
		MultipartFile imgFile = request.getFile("file");

		Log.info("[In UserController >> userprofile :{}, call to userService upadteUser method");
		return userService.updateUser(mailId, password, company, designation, imgFile, session);
	}

	/**
	 * @return status of success or failure
	 */
	@RequestMapping(value = "/viewUser", method = RequestMethod.GET)
	public List<User> userdetail() {

		Log.info("[In UserController >> userdetail :{}, call to userService viewUser method ");
		return userService.viewUser();
	}
	/**
	 * @return status of success or failure
	 * from the page viewProfile.js
	 */
	@RequestMapping(value = "/viewProfileByID", method = RequestMethod.POST)
	public User viewProfileByID(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {

		Log.info("[In UserController >> userdetail :{}, call to userService viewUser method ");
		int userId=Integer.parseInt(request.getParameter("userId"));
		return userService.findUserById(userId);
	}
	/**
	 * @param email
	 *            Getting the email from User for Recover Password
	 * @return status of success or failure
	 */
	@RequestMapping(value = "/forgetpassword", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseDto findUser(@RequestParam(value = "email") final String email) {
		ResetPasswordDto resetpasswordDto = new ResetPasswordDto();
		resetpasswordDto.setEmail(email);
		Log.info("[In UserController >> findUser :{}, call to userService sendNotification method");
		return notificationService.sendNotification(resetpasswordDto);
	}

}
