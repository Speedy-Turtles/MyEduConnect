package app.project.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import app.project.entities.User;
import app.project.repository.UserRepository;


@Service
public class UserService {
 
	  @Autowired
	  UserRepository UserRepo;
	  
	  
	  public User getLoggedUser() {
			 try {
				 Authentication loggeduser=SecurityContextHolder.getContext().getAuthentication();
				 return UserRepo.getUserByemail(loggeduser.getName());
			 }catch(Exception e) {
				 e.printStackTrace();
				 return null;
			 }
		 }
	  
	  public User getByEmail(String email) {
		 try {
			 return UserRepo.getUserByemail(email);
		 }catch(Exception e) {
			 e.printStackTrace();
			 return null;
		 }
		 
	  }
	  
	  
	  public void VerifyEmail(String email) throws Exception {
		  User user=UserRepo.getUserByemail(email);
		  if(user==null) {
			  throw new Exception("User Not Found");
		  }else {
			  if(user.getEmail_verified_at()!=null) {
				  throw new Exception("User Already Verified");
			  }else {
				  user.setEmail_verified_at(new Date());
				  UserRepo.save(user);
			  }
		  }
	  }
	  
	  
	
}
