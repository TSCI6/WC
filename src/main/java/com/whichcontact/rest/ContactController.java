package com.whichcontact.rest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.whichcontact.core.entity.Contacts;
import com.whichcontact.core.service.ContactService;
import com.whichcontact.core.service.ResponseDto;
import com.whichcontact.rest.GoogleContact.RootObject;

/**
 * @author TS001127
 *
 *         the backside to the ajax call in uploadContacts.js and contacts.js
 */
@RequestMapping("/whichcontact")
@Controller
public class ContactController {

	private static final Logger Log = Logger.getLogger(ContactController.class.getName());
	@Inject
	private ContactService contactService;

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * 
	 * @param user
	 *            the Raw json response from api from UploadContacts.js for
	 *            mapping the google contacts json into gson and parsing and
	 *            saving
	 * 
	 *
	 * 
	 */
	@RequestMapping(value = "/SaveContacts", method = RequestMethod.POST)
	@ResponseBody
	protected ResponseDto SaveContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResponseDto responseDto = new ResponseDto();
		response.setContentType("text/html;charset=UTF-8");
		try {
			RootObject rootObj = (new Gson().fromJson(request.getParameter("user"), RootObject.class));
			int totalContacts = Integer.parseInt(rootObj.getFeed().getOpenSearchTotalResults().getT());
			responseDto = contactService.SaveContacts(rootObj.getFeed().getEntry(), totalContacts);
			Log.info("In ContactController >> SaveContacts(), calls the ContactService>>SaveContacts()::SUCCESS");
			return responseDto;

		} catch (Exception e) {
			Log.error("In ContactController >> SaveContact{},  SAVE FAILED ");
		}
		return responseDto;
	}

	/*
	 * from contacts.js for viewing the contacts of the person logged in
	 * 
	 * @return
	 * 
	 */
	@RequestMapping(value = "/viewcontact", method = RequestMethod.GET)
	@ResponseBody
	public List<Contacts> userdetail() {
		List<Contacts> response = new ArrayList<Contacts>();
		try {
			response.addAll(contactService.viewContact());
			Log.info("In ContactController >> userdetail{}, calls the ContactService viewContact method");			
		} catch (Exception e) {
			Log.error("^ERROR ^Showing Contacts in ContactController >>viewContact Method ");
		}
		return response;
	}
	@RequestMapping(value = "/viewContactsForProfile", method = RequestMethod.POST)
	@ResponseBody
	public List<Contacts> viewContactsForProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		List<Contacts> viewContactsOfProfile = new ArrayList<Contacts>();
		try {
			viewContactsOfProfile.addAll(contactService.viewContactsForProfile(Integer.parseInt(request.getParameter("userId"))));
			Log.info("In ContactController >> viewProfile{}, calls the ContactService viewContactsForProfile method");			
		} catch (Exception e) {
			Log.error("^ERROR ^Showing Contacts in ContactController >>viewContactsForProfile Method ");
		}
		return viewContactsOfProfile;
	}
	/**
	 * @param path
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalStateException
	 * @throws ServletException
	 * @throws IOException
	 * 
	 * 
	 *             from UploadContacts.js for reading the uploaded csv DATA
	 *             ENRICHMENT adding job and company of the contacts
	 * 
	 * 
	 * 
	 */
	@RequestMapping(value = "/SaveCsv", method = RequestMethod.POST)
	@ResponseBody
	protected ResponseDto SaveCsv(MultipartHttpServletRequest request) throws IllegalStateException, IOException {
		ResponseDto rd = new ResponseDto();
		MultipartFile csvFile = request.getFile("file");
		File convFile = new File("d:\\newfile.csv");
		csvFile.transferTo(convFile);
		String absolutePath = convFile.getAbsolutePath();

		try {
			CSVReader reader = new CSVReader(new FileReader(absolutePath));
			// List<String[]> myEntries = reader.
			List<String[]> myEntries = reader.readAll();
			rd = contactService.SaveCSV(myEntries);

		} catch (IOException e) {
			Log.error("^ERROR SAVING CSV^");
		}
		Log.info("In ContactController >> SaveCsv(), calls the ContactService SaveCSV method");
		return rd;

	}

}