package com.whichcontact.core.service;

import static java.util.Objects.nonNull;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.whichcontact.core.entity.User;
import com.whichcontact.core.jpa.UserLoginRepository;

@Service
public class NotificationService {
    private JavaMailSender javaMailSender;
    @Inject
    private UserLoginRepository userLoginRepository;
    @Autowired
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    /**
     * @param resetpasswordDto
     * @return
     * @throws MailException
     */
    public ResponseDto sendNotification(ResetPasswordDto resetpasswordDto) throws MailException {
        User user = userLoginRepository.findByEmail(resetpasswordDto.getEmail());
        ResponseDto responseDto = new ResponseDto();
        if (nonNull(user)) {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(user.getEmail());
            mail.setFrom("whichcontactv2@gmail.com");
            mail.setSubject("Forget password link");
            mail.setText("here is your password" + " = " + user.getPassword());
            javaMailSender.send(mail);
            responseDto.setStatus(200);
            responseDto.setMessage("Successfully mail send ");
        } else {
            responseDto.setStatus(400);
            responseDto.setMessage("failed ");
        }
        return responseDto;
    }
}