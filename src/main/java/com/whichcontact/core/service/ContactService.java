package com.whichcontact.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.whichcontact.core.entity.Contacts;
import com.whichcontact.core.entity.Invitation;
import com.whichcontact.core.jpa.ContactRepository;
import com.whichcontact.core.jpa.InvitationRepository;
import com.whichcontact.rest.ContactController;
import com.whichcontact.rest.GoogleContact.Entry;
import com.whichcontact.rest.GoogleContact.GdPhoneNumber;

/**
 * @author TS001127
 *
 */
@Service
public class ContactService {
	/*
	 * calls from contact controller and search controller directly deals with
	 * contactRepository
	 * 
	 * 
	 */
	private static final Logger Log = Logger.getLogger(ContactController.class.getName());

	@Inject
	private ContactRepository contactRepository;
	Integer user_id;
	@Inject
	private InvitationRepository invitationrepository;

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
	 * 
	 */

	public ResponseDto SaveContacts(ArrayList<Entry> entry, int newContacts) {
		ResponseDto responseDto = new ResponseDto();
		Contacts contacts = new Contacts();
		user_id = UserService.getUser().getId();
		int continuingContactId = (int) contactRepository.count();
		Log.info("existing Contacts=" + continuingContactId + "::new Contacts=" + newContacts);
		for (int index = 0; index < newContacts; index++) // id
		{
			contacts.setContactId(continuingContactId);
			continuingContactId++;
			contacts.setUserId(user_id);
			int z = entry.get(index).getId().getT().toString().lastIndexOf('/') + 1;
			contacts.setGid(entry.get(index).getId().getT().substring(z)); // gid
			contacts.setName(entry.get(index).getTitle().getT().toString());
			if (entry.get(index).getGdEmail() != null)
				contacts.setEmail(entry.get(index).getGdEmail().get(0).getAddress().toString()); // email
			else {
				contacts.setEmail(null);
			}
			ArrayList<String> teleNumbers = numberMapper(entry.get(index).getGdPhoneNumber());
			contacts.setMobile(teleNumbers.get(0));
			contacts.setPhone(teleNumbers.get(1));
			contacts.setWork(teleNumbers.get(2));
			if (entry.get(index).getGdPostalAddress() != null)
				contacts.setPostal(entry.get(index).getGdPostalAddress().get(0).getT().toString()); // email
			else {
				contacts.setPostal(null);
			}
			try {
				contactRepository.save(contacts);
			} catch (Exception e) {
				responseDto.setStatus(400);
				Log.error("Error while saving the contacts while repository call ");

			}
		}

		if (responseDto.getStatus() != 400) {
			responseDto.setMessage("Contacts added succesfully");
			responseDto.setStatus(200);
		} else
			responseDto.setMessage("Contacts adding Failed");
		return responseDto;
	}

	private ArrayList<String> numberMapper(ArrayList<GdPhoneNumber> gdPhoneNumber) {
		ArrayList<String> teleNumbers = new ArrayList<String>();
		if (gdPhoneNumber != null) {
			teleNumbers.add(0, gdPhoneNumber.get(0).getT());
			try {
				teleNumbers.add(1, gdPhoneNumber.get(1).getT());
			} catch (Exception e) {
				teleNumbers.add(1, "+91" + gdPhoneNumber.get(0).getT());
			}
			try {
				teleNumbers.add(2, gdPhoneNumber.get(2).getT());
			} catch (Exception e) {
				teleNumbers.add(2, null);
			}
			return teleNumbers;
		} else {
			teleNumbers.add(0, null);
			teleNumbers.add(1, null);
			teleNumbers.add(2, null);
			return teleNumbers;
		}
	}

	/**
	 * @return data from table contacts
	 */
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
		Log.info("CSV ENTRIES" + myEntries.size());
		for (int index = 1; index < myEntries.size(); index++) {

			HashSet<Contacts> csvCommonContacts = new HashSet<Contacts>();
			csvCommonContacts.addAll(contactRepository
					.findByNameContaining("%" + myEntries.get(index)[0] + " " + myEntries.get(index)[1] + "%"));
			csvCommonContacts.addAll(contactRepository.findByEmail(myEntries.get(index)[2]));
			for (Contacts contacts : csvCommonContacts) {
				if (contacts.getEmail() == null) {
					contacts.setEmail(myEntries.get(index)[2].toString());
				} else
					contacts.setWork(myEntries.get(index)[2].toString());
				contacts.setCompany(
						myEntries.get(index)[3].toString().isEmpty() ? null : myEntries.get(index)[3].toString());
				contacts.setDesignation(
						myEntries.get(index)[4].toString().isEmpty() ? null : myEntries.get(index)[4].toString());
				contactRepository.save(contacts);
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

	public void saveEmail(List<String> list) {
		try {

			Integer user_id = UserService.getUser().getId();
			for (String List : list) {
				{
					Invitation invitation = new Invitation();
					invitation.setUserId(user_id);
					invitation.setEmail(List);

					invitationrepository.save(invitation);
				}

			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	
	public List<Contacts> viewContactsForProfile(int userIdWanted) { // from contactController

		List<Contacts> contacts = contactRepository.findByUserId(userIdWanted);
		Log.info("In ContactService >> viewContact() , Hits the ContactsRepository ");
		return contacts;
	}

}