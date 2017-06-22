package com.whichcontact.core.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.whichcontact.core.entity.Invitation;
import com.whichcontact.core.jpa.InvitationRepository;

/**
 * @author AS001136 This class is use for send a mail to unregister contacts for
 *         join which contact
 */
@Service
public class InvitationNotificationService {
	private static final Logger LOGGER = Logger.getLogger(InvitationNotificationService.class.getName());
	private JavaMailSender javaMailSender;
	@Inject
	private InvitationRepository invitationrepo;

	@Autowired
	public InvitationNotificationService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	/**
	 * @param resetpasswordDto
	 * @return ResponseDto set 200 if mail send successfully.
	 */

	public ResponseDto sendNotification() {
		try {
			Integer user_id = UserService.getUser().getId();
			List<Invitation> list;
			list = invitationrepo.findByUserId(user_id);

			ResponseDto responseDto = new ResponseDto();
			for (Invitation listElement : list) {
				String email = listElement.getEmail();
				SimpleMailMessage mail = new SimpleMailMessage();
				mail.setTo(email);
				mail.setFrom("whichcontactv2@gmail.com");
				mail.setSubject("whichContact Invitation");
				mail.setText("Please Join which contact here is the url---->www.whichContact.com");
				javaMailSender.send(mail);
				responseDto.setMessage("Successfully mail send ");
			}

			return responseDto;
		} catch (MailException e) {
			LOGGER.info("Some technical issue we are working on this" + e);
		}
		return null;
	}
}
