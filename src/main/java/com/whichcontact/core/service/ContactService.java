package com.whichcontact.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.whichcontact.core.entity.Contacts;
import com.whichcontact.core.jpa.ContactRepository;
import com.whichcontact.rest.ContactController;
import com.whichcontact.rest.GoogleContact.Entry;

/**
 * @author TS001127
 *
 */
@Service
public class ContactService {
	/*
	 * the server side java code to handle the calls from contact controller and
	 * search controller directly deals with contactRepository and makes the use
	 * of spring boot and jpa
	 * 
	 */
	private static final Logger Log = Logger.getLogger(ContactController.class.getName());

	@Inject
	private ContactRepository contactRepository;
	Integer user_id;

	/**
	 * @param entry
	 *            --the json from google api have been mapped and parsed into an
	 *            array list of Entry class
	 * @param totalContacts
	 *            -- the total num of contacts returned by the google api
	 * @return
	 * 
	 * 
	 * 		from uploadContacts.js > ContactController > SaveContact
	 * 
	 *         the saveContacts method is called from the contactController >>
	 *         SaveContact method it makes a flattened object of contact class
	 *         and fills the values in it by getters method of the entry class
	 *         it then saves the contact object for each entry class into the
	 *         repository
	 */

	public ResponseDto SaveContacts(ArrayList<Entry> entry, int totalContacts) {

		ResponseDto responseDto = new ResponseDto();
		Contacts contacts = new Contacts();

		user_id = UserService.getUser().getId();
		for (int i = 0; i < totalContacts; i++) // id
		{
			contacts.setContactId(i);
			contacts.setUserId(user_id);

			try {
				int z = entry.get(i).getId().getT().toString().lastIndexOf('/') + 1;
				contacts.setGid(entry.get(i).getId().getT().substring(z)); // gid
			} catch (Exception e) {
				contacts.setGid(null);
			}

			try {
				contacts.setName(entry.get(i).getTitle().getT().toString()); // name
			} catch (Exception e) {
				contacts.setName(null);
			}

			try {
				contacts.setEmail(entry.get(i).getGdEmail().get(0).getAddress().toString()); // email
			} catch (Exception e) {

				contacts.setEmail(null);

			}
			try {
				contacts.setMobile(entry.get(i).getGdPhoneNumber().get(0).getT()); // mobile
			} catch (Exception e) {

				contacts.setMobile(null);

			}
			try {
				contacts.setWork(entry.get(i).getGdPhoneNumber().get(1).getT()); // work
			} catch (Exception e) {

				contacts.setWork(null);

			}
			try {
				contacts.setPhone(entry.get(i).getGdPhoneNumber().get(2).getT()); // phone
			} catch (Exception e) {

				contacts.setPhone(null);

			}
			try {
				contacts.setPostal(entry.get(i).getGdPostalAddress().get(0).getT().toString()); // gid
			} catch (Exception e) {

				contacts.setPostal(null);

			}
			try {
				contactRepository.save(contacts);
			} catch (Exception e) {
				Log.error("Error while saving the contacts while repository call ");

			}
		}

		responseDto.setStatus(200);
		responseDto.setMessage("User added succesfully");


		return responseDto;
	}

	public List<Contacts> viewContact() { // from contactController

		List<Contacts> contacts = contactRepository.findByUserId(UserService.getUser().getId());
		Log.info("In ContactService >> viewContact() , Hits the ContactsRepository ");
		return contacts;
	}

	/**
	 * @param myEntries
	 * @return
	 * 
	 * 		from uploadContacts.js > ContactController > SaveCsv
	 * 
	 *         update in db at contacts emails from the linkedin csv on basis of
	 *         name
	 *         
	 */
	public ResponseDto SaveCSV(List<String[]> myEntries) {

		ResponseDto responseDto = new ResponseDto();
		System.out.println(myEntries.size());

		for (int i = 1; i < myEntries.size(); i++) {
			try {
				Contacts co = contactRepository.findByNameContaining("%" + myEntries.get(i)[0] + " " + myEntries.get(i)[1] + "%");
				System.out.println(i + 1 + "--" + co.getName() + "--" + myEntries.get(i)[0] + "--" + myEntries.get(i)[1] + "--");
				co.setEmail(myEntries.get(i)[2].toString());
				contactRepository.save(co);
			} catch (Exception e) {
				Log.error("Error while saving the CSV while repository call ");
			}
		}
		for (int i = 1; i < myEntries.size(); i++) {
			try {
				Contacts c = contactRepository.findByEmail(myEntries.get(i)[2]);
				System.out.println(i + 1 + "**" + c.getName() + "**" + myEntries.get(i)[2] + "**" + myEntries.get(i)[3]
						+ "**" + myEntries.get(i)[4] + "**");
				c.setCompany(myEntries.get(i)[3].toString());
				c.setDesignation(myEntries.get(i)[4].toString());
				contactRepository.save(c);

			} catch (Exception e) {
				Log.error("Error while saving the CSV while repository call ");
			}

		}
		responseDto.setStatus(200);
		responseDto.setMessage("Contacts added succesfully");
		Log.info("In ContactService >> SaveCSV() , Hits the ContactsRepository ");

		return responseDto;
	}

	/**
	 * @param coname
	 * @return
	 * 
	 * 		from searchContacts.js > SearchController > searchByCompany
	 * 
	 *         update in db at contacts from the linkedin csv on basis of email
	 * 
	 */
	public List<Contacts> SearchCompany(String coName) {
		List<Contacts> contacts = contactRepository.findByCompanyStartingWith("%" + coName + "%");
		Log.info("In ContactService >> SaveCSV() , Hits the ContactsRepository ");

		return contacts;
	}

	/**
	 * @param user
	 * @return
	 *
	 * 		from searchContacts.js > SearchController > searchByName
	 * 
	 *         Searches for name in the contacts table
	 *         
	 *         
	 */
	public List<Contacts> SearchName(String user) {
		List<Contacts> contacts = contactRepository.findByNameStartingWith("%" + user + "%");
		Log.info("In ContactService >> SearchName() , Hits the ContactsRepository ");

		return contacts;

	}

	/**
	 * @param user
	 * @return
	 * 
	 * 		from searchContacts.js > SearchController > searchByName
	 * 
	 *         Searches for contacts in the contact table by the email
	 * 
	 *
	 */
	public Collection<Contacts> SearchEmail(String user) {
		List<Contacts> contacts = contactRepository.findByEmailStartingWith("%" + user + "%");
		Log.info("In ContactService >> SearchEmail() , Hits the ContactsRepository ");
		return contacts;
	}

}