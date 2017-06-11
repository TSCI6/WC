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
 */
/**
 * @author TS001127
 *
 */
@RequestMapping("/whichcontact")
@Controller
public class ContactController {
	/*
	 * the backside to the ajax call in uploadContacts.js and contacts.js
	 * 
	 * 
	 */

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
	 *
	 * from UploadContacts.js for mapping the google contacts json into gson and
	 * then parsing it and then saving it the google contacts json is in the
	 * parameter user
	 * 
	 *
	 */
	@RequestMapping(value = "/SaveContacts", method = RequestMethod.POST)
	@ResponseBody
	protected ResponseDto SaveContact(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResponseDto rd = new ResponseDto();
		response.setContentType("text/html;charset=UTF-8");
		try {
			RootObject rootObj = (new Gson().fromJson(request.getParameter("user"), RootObject.class));
			System.out.println(rootObj.getFeed().getOpenSearchTotalResults().getT());
			int totalContacts = Integer.parseInt(rootObj.getFeed().getOpenSearchTotalResults().getT());
			rd = contactService.SaveContacts(rootObj.getFeed().getEntry(), totalContacts);
			Log.info("In ContactController >> SaveContact{}, calls the ContactService SaveContacts method");
			return rd;

		} catch (Exception e) {
			Log.error(
					"^ERROR SAVING CONTACTS FROM GOOGLE^\n1)make sure u're connected to internet for this step\n2)do google login in the browser");
		}
		Log.info("In ContactController >> SaveContact{}, calls the ContactService SaveContacts method");
		return rd;
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
		} catch (Exception e) {
			Log.error("^ERROR ^Showing Contacts in ContactController >>userDetail Method ");
		}
		Log.info("In ContactController >> userdetail{}, calls the ContactService viewContact method");

		return response;
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
	 * from UploadContacts.js for reading the uploaded csv and saving the
	 * additional details for the common contacts DATA ENRICHMENT PROCESS for
	 * adding job and company of the contacts
	 
	 * 
	 * 
	 */
	@RequestMapping(value = "/SaveCsv", method = RequestMethod.POST)
	@ResponseBody
	protected ResponseDto SaveCsv(MultipartHttpServletRequest request) throws IllegalStateException, IOException
			 {
		ResponseDto rd = new ResponseDto();
		MultipartFile csvFile = request.getFile("file");
		File convFile = new File( "d:\\newfile.csv");
		csvFile.transferTo(convFile);
		String absolutePath = convFile.getAbsolutePath();
		//System.out.println(convFile.getAbsolutePath()+"--"+convFile.getCanonicalPath()+"--"+convFile.getCanonicalFile()+"--"+convFile.getPath());
		
		try {
			CSVReader 	reader = new CSVReader(new FileReader(absolutePath));
			//List<String[]> myEntries = reader.
			List<String[]> myEntries = reader.readAll();
			rd = contactService.SaveCSV(myEntries);

		} catch (IOException e) {
			Log.error("^ERROR SAVING CSV^");
		}
		Log.info("In ContactController >> SaveCsv(), calls the ContactService SaveCSV method");
		return rd;

	}

}