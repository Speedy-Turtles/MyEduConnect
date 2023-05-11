package app.project.service;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import app.project.entities.User;
import app.project.mail.Mail;
import app.project.repository.UserRepository;
import net.bytebuddy.utility.RandomString;


@Service
public class UserService {
 
	  @Autowired
	  UserRepository UserRepo;
	  
	  @Autowired
	  Mail mailsender;
	  
	  
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
	  
	  public void forgotPassword(String email)throws Exception {
		  User user=UserRepo.getUserByemail(email);
		  if(user==null) {
			  throw new Exception("Email Not Found");
		  }else {
			  String Token=RandomString.make(8);
			  user.setPassword_token(Token);
			  user.setPassword_token_send_ats(new Date());
			  UserRepo.save(user);
			  mailsender.SendForgotPassword(user, Token);
		   }
	  }
	  
	  public void ChangerPassword(String email,String Token,String password)throws Exception {
		  User user=UserRepo.getUserByemail(email);
		  if(user==null) {
			  throw new Exception("Email Not Found");
		  }else {
			 
		   }
	  }
	  
	  
	
}
