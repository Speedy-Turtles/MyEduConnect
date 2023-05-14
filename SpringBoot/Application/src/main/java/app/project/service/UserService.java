package app.project.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import app.project.SpringSecurity.SecurityConfig;
import app.project.SpringSecurity.UserDetailsImpl;
import app.project.entities.Notification;
import app.project.entities.Role;
import app.project.entities.User;
import app.project.jwt.jwtTokenUtil;
import app.project.mail.Mail;
import app.project.repository.ClasseRepository;
import app.project.repository.NotificationRepository;
import app.project.repository.RoleRepository;
import app.project.repository.SpecialiteRepository;
import app.project.repository.UserRepository;
import app.project.repository.UserRoleRepository;
import authPrametre.StatId;
import net.bytebuddy.utility.RandomString;

@Service
public class UserService {

		@Autowired
		Mail mailsender;

	    @Autowired
	    UserRepository UserRepo;
   
	    @Autowired
		private jwtTokenUtil  jwtTokenUtil;
    
	    @Autowired
	    UserRoleRepository roleuser;
	    
	    @Autowired
		NotificationRepository notifrepo;

	  /*public User getLoggedUser() {
			 try {
				 Authentication loggeduser=SecurityContextHolder.getContext().getAuthentication();
				 return UserRepo.getUserByemail(loggeduser.getName());
			 }catch(Exception e) {
				 e.printStackTrace();
				 return null;
			 }
		 }*/
	    
	    
	    // Get User Authentifie
	    
	    public User UserAuth(HttpServletRequest request) {
	    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    	 final String requestTokenHeader=request.getHeader("Authorization");
	    	 String jwtToken=requestTokenHeader.substring(7);
	    	 String username=jwtTokenUtil.getUsernameFromToken(jwtToken);
	    	 return UserRepo.getUserByemail(username);
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
			  if(UserRepo.CheckToken(Token)==null) {
				  throw new Exception("Token Not Found");
			  }else {
				  if(user.getPassword_token().equals(Token)) {
					  user.setPassword(password);
					  user.setPassword_token(null);
					  user.setPassword_token_send_ats(null);
					  UserRepo.save(user);
				  }else {
					  throw new Exception("Token doesn't match");
				  }
			  }
		   }
	  }
	  
	  public  boolean TestRoleName(String email) {
		  User user=UserRepo.getUserByemail(email);
		  List<Role> role=user.getRole();
		  boolean test=false;
		  if(role.size()>1) {
			  if(role.get(0).getRoleName().equals("ensignant")) {
				  return true;
			  }else if(role.get(0).getRoleName().equals("chefDepartment")) {
				  return true;
			  }
		  }else {
			  if(role.get(0).getRoleName().equals("etudiant")) {
				  return false;
			  }else if(role.get(0).getRoleName().equals("Admin")) {
				  return true;
			  }else if(role.get(0).getRoleName().equals("ensignant")) {
				  return false;
			  }
		  }
		  return test;
	  }
	  
	  public void SendNotification(StatId param) {
		  User user=UserRepo.getUserById(param.getId());
		  User chef=UserRepo.GetChefDepartment();
			 Notification notif=new Notification();
			 notif.setUserEnvoi(chef);
			 notif.setEtat(0);
			 notif.setUserRecu(user);
		  if(param.getStatus()==1) {
				notif.setMessage("Chef Department Accepte Your Request");
		  }else {
				notif.setMessage("Chef Department Reject Your Request");
		  }
		  notifrepo.save(notif);
	  }
}
