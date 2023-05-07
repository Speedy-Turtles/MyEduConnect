package app.project.service;

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
}
