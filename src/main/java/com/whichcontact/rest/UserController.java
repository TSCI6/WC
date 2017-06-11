package com.whichcontact.rest;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.whichcontact.core.entity.User;
import com.whichcontact.core.service.NotificationService;
import com.whichcontact.core.service.ResetPasswordDto;
import com.whichcontact.core.service.ResponseDto;
import com.whichcontact.core.service.UserService;

@RequestMapping("/whichcontact")
@RestController
public class UserController {
	  
 @Inject
 private UserService userService;
 User user;
 @Inject
 private NotificationService notificationService;
 @RequestMapping(value = {""}, method = RequestMethod.GET)
 public ModelAndView login() {
  ModelAndView modelAndView = new ModelAndView();
  modelAndView.setViewName("login");
  return modelAndView;
 }
 /**
  * @param username getting the username from ui
  * @param password getting the password from ui 
  * @return the status of success or failure 
  */
 @RequestMapping(value="/login", method = RequestMethod.POST)
 public ResponseDto checkUser(@RequestParam(value = "username", required = true) final String username,
  @RequestParam(value = "password", required = true) final String password) {
  return userService.authorizeUser(username, password);
 }
 /** The saveUser method is use for save the Value of User During The Registration
  * @param username Getting Username from user
  * @param mailId  Getting mailId from user
  * @param password Getting password from user 
  * @param company Getting company from user
  * @param designation Getting Designation from user
  * @return the status of success or failure
  */
 @RequestMapping(value = "/addUser", method = RequestMethod.POST)
 public ResponseDto RegisterUser(@RequestParam(value = "username", required = true) final String username,
  @RequestParam(value = "mailId", required = true) final String mailId,
  @RequestParam(value = "password", required = true) final String password,
  @RequestParam(value = "company", required = true) final String company,
  @RequestParam(value = "designation", required = true) final String designation){
  return userService.saveUser(username, mailId, password, company, designation);
  }
 /** The userProfile method is use for edit the UserProfile
  * @param mailid Getting new MailId from user
  * @param password Getting new password from user
  * @param company Getting new Company from User
  * @param designation Getting new Designtaion from User
  * @param image Getting user Profile picture
  * @return the status of success or failure
  */
 @RequestMapping(value = "/editUserProfile", method = RequestMethod.POST)
 public ResponseDto userProfile(
   @RequestParam(value = "mailid", required = true) final String mailid,
   @RequestParam(value = "password", required = true) final String password,
   @RequestParam(value = "company", required = true) final String company,
   @RequestParam(value = "designation", required = true) final String designation)
    {
   return userService.editUser(mailid, password, company, designation);
  }
  /**
   * @return status of success or failure
   */
 @RequestMapping(value = "/viewUser", method = RequestMethod.GET)
 public List < User > userdetail() {
   return userService.viewUser();
}
  /**
   * @param email Getting the email from User for Recover Password
   * @return  status of success or failure
   */
 @RequestMapping(value = "/forgetpassword", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
 @ResponseBody
 public ResponseDto findUser(
  @RequestParam(value = "email", required = true) final String email) {
  ResetPasswordDto resetpasswordDto = new ResetPasswordDto();
  resetpasswordDto.setEmail(email);
  return notificationService.sendNotification(resetpasswordDto);
 }
 
 @RequestMapping(value = "/uploadImage", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
 
	public @ResponseBody ResponseDto uploadImage(@RequestParam("File") MultipartFile image,  final HttpServletRequest request,
			final HttpServletResponse response) throws IOException {

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT");
		response.setHeader("Access-Control-Allow-Headers", "*");
		response.setHeader("Access-Control-Max-Age", "3600");
		return userService.saveImage(image);
	}

}
  
 

 
 