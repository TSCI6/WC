package com.whichcontact.core.service;

import static java.util.Objects.nonNull;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.whichcontact.core.entity.User;
import com.whichcontact.core.jpa.UserLoginRepository;

@Service
public class UserService {
	private static final Logger Log = Logger.getLogger(UserService.class.getName());
	private static final String UPLOAD_DIRECTORY = "/uploadImage";
	static User user;
	@Inject
	private UserLoginRepository userLoginRepository;

	/**
	 * @param name
	 *            Getting the UserName
	 * @param password
	 *            Getting the User password
	 * @return ResponseDto if the UserName and Password is correct then status
	 *         is 200 otherwise 400.
	 */
	public ResponseDto authorizeUser(String name, String password) {
		User user = userLoginRepository.findByNameAndPassword(name, password);
		setUser(user);
		ResponseDto responseDto = new ResponseDto();
		if (nonNull(user)) {
			responseDto.setStatus(200);
			responseDto.setMessage("Success");

		} else {
			responseDto.setStatus(401);
			responseDto.setMessage("Failed");
		}
		return responseDto;
	}

	/**
	 * @param username
	 * @param mailId
	 * @param password
	 * @param company
	 * @param designation
	 * @return ResponseDto if the user does not Pre-exist in that case the
	 *         status will be 200 otherwise 400
	 */

	public ResponseDto saveUser(String username, String mailId, String password, String company, String designation) {
		ResponseDto responseDto = new ResponseDto();
		User checkUser = userLoginRepository.findByNameAndPassword(username, password);
		if (nonNull(checkUser)) {
			responseDto.setStatus(400);
			responseDto.setMessage("user already exist");
		} else {
			User user = new User();
			user.setName(username);
			user.setPassword(password);
			user.setEmail(mailId);
			user.setCompany(company);
			user.setDesignation(designation);
			try {
				userLoginRepository.save(user);
				responseDto.setStatus(200);
				responseDto.setMessage("User added succesfully");
			} catch (Exception e) {
				Log.error("Error while adding User");
			}
		}
		return responseDto;
	}

	/**
	 * @param mailId
	 * @param password
	 * @param company
	 * @param designation
	 * @param image
	 * @return ResponseDto if the user successfully updated the profile then
	 *         status is 200.
	 */
	public ResponseDto updateUser(String mailId, String password, String company, String designation,
			MultipartFile file, HttpSession session) throws IOException {
		ResponseDto responseDto = new ResponseDto();
		User user = getUser(); // The same user as in login time
		user.setEmail(mailId);
		user.setPassword(password);
		user.setCompany(company);
		user.setDesignation(designation);
		ServletContext context = session.getServletContext();
		String path = context.getRealPath(UPLOAD_DIRECTORY);
		String filename = file.getOriginalFilename();
		user.setImage(path);
		System.out.println(path + " " + filename);

		try {
			userLoginRepository.save(user);
			byte[] bytes = file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File(path + File.separator + filename)));
			stream.write(bytes);
			stream.flush();
			stream.close();
			responseDto.setStatus(200);
			responseDto.setMessage("userProfile Updated succesfully");
		}

		catch (Exception e) {
			Log.error("user not updated ");
		}

		return responseDto;
	}

	public static User getUser() {
		return user;
	}

	public void setUser(User user) {
		UserService.user = user;
	}

	public List<User> viewUser() {
		User user = getUser();
		List<User> list = new ArrayList<User>();
		list.add(user);
		return list;
	}

	/**
	 * @param user2
	 * @return User
	 */
	public User findUserById(int user2) {
		User user = userLoginRepository.findById(user2);
		return user;
	}

}
