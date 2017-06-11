package com.whichcontact.core.service;
import static java.util.Objects.nonNull;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.whichcontact.core.entity.User;
import com.whichcontact.core.jpa.UserLoginRepository;


@Service
public class UserService {
	
private static String UPLOADED_FOLDER = "E:\\temp\\";
 static User user;
 @Inject
 private UserLoginRepository userLoginRepository;
 /**
 * @param name Getting name from User Controller 
 * @param password Getting password from User Controller
 * @return ResponseDto
 */
public ResponseDto  authorizeUser(String name, String password) {
  User user = userLoginRepository.findByNameAndPassword(name, password);
  setUser(user);
  ResponseDto responseDto = new ResponseDto();
  if (nonNull(user)) {
   responseDto.setStatus(200);
   responseDto.setMessage("Success");
 
  }
  else {
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
 * @return ResponseDto
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


   userLoginRepository.save(user);
   responseDto.setStatus(200);
   responseDto.setMessage("User added succesfully");

  }
  return responseDto;
 }


 /**
 * @param mailId
 * @param password
 * @param company
 * @param designation
 * @param image
 * @return
 */
public ResponseDto editUser(String mailId, String password, String company, String designation ) {
  ResponseDto responseDto = new ResponseDto();
  User user = getUser(); // The same user as in login time
  user.setEmail(mailId);
  user.setPassword(password);
  user.setCompany(company);
  user.setDesignation(designation);
 


  userLoginRepository.save(user);
  responseDto.setStatus(200);
  responseDto.setMessage("User Updated succesfully");


  return responseDto;
 }


public static User getUser() {
	return user;
}

public void setUser(User user) {
	UserService.user = user;
}

 public List < User > viewUser() {
  User user = getUser();
  List < User > list = new ArrayList < User > ();
  list.add(user);
  return list;
 }
 
public ResponseDto saveImage(MultipartFile image) throws IOException{
	
	ResponseDto responseDto = new ResponseDto();
	
	  // Get the file and save it somewhere
     byte[] bytes =image.getBytes();
     Path path =Paths.get(UPLOADED_FOLDER +image.getOriginalFilename());
  // String imageEncodedUrl = Base64.encode(bytes);
     Files.write(path, bytes);
   // user.setImage(path);
    // userLoginRepository.save(user);
     responseDto.setStatus(200);
     responseDto.setMessage("User Image Updated succesfully");

     return responseDto; 
 }
public  User findUserById(int user2) {      //fromSearchController.java for finding the name and id of the mutual contact
	// TODO Auto-generated method stub
User user=userLoginRepository.findById(user2);
	return user;
} 


 }
 
 
