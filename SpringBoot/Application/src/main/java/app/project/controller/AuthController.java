<<<<<<< HEAD
package app.project.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.project.SpringSecurity.SecurityConfig;
import app.project.SpringSecurity.UserDetailsImpl;
import app.project.entities.User;
import app.project.repository.UserRepository;
import app.project.service.UserService;
import authPrametre.ChangerPassword;
import authPrametre.Credentials;
import authPrametre.Reponse;
import app.project.jwt.jwtTokenUtil;
import app.project.mail.Mail;


@RestController
public class AuthController {
	
        
        @Autowired
	    AuthenticationManager authenticationManager; 
	 
	    @Autowired
	    UserRepository UserRepo;
	
	    @Autowired
		UserDetailsImpl userservice;
	    
	    @Autowired
	    UserService user_service;
	    
	    @Autowired
		private jwtTokenUtil  jwtTokenUtil;
	    
	    @Autowired
	    SecurityConfig securityConfig;
	    
	    @Autowired
	    Mail mailsender;
	    

	@PostMapping("/SignUp")
	public ResponseEntity<?>  SignUp(@RequestBody User user){
		
		if(UserRepo.getUserByemail(user.getEmail())!=null) {
			return new ResponseEntity<String>("Email already Used",HttpStatus.CONFLICT);
		}
		
		if(UserRepo.getUserByCin(user.getCin())!=null) {
			return new ResponseEntity<String>("Cin already Used",HttpStatus.CONFLICT);
		}
		
		User new_user=new User();
		new_user.setBirth_day(user.getBirth_day());
		new_user.setCin(user.getCin());
		new_user.setEmail(user.getEmail());
		new_user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
		new_user.setFirstName(user.getFirstName());
		new_user.setLastName(user.getLastName());
		new_user.setNum_tlf(user.getNum_tlf());
		
		if(user.getPhoto()==null) {
			new_user.setPhoto(user.getFirstName().toUpperCase().charAt(0)+""+user.getLastName().toUpperCase().charAt(0));
		}else {
			new_user.setPhoto(user.getPhoto());
		}
		
		new_user.setRole(user.getRole());
		
		try {
			mailsender.sendVerificationEmail(user);
		}catch(MessagingException e) {
			return new ResponseEntity<String>("Error Connexion",HttpStatus.CONFLICT);
		}catch(UnsupportedEncodingException e) {
			return new ResponseEntity<String>("Unsupported Forme",HttpStatus.CONFLICT);
		}
	
		UserRepo.save(new_user);

		return  ResponseEntity.ok().body("user add");
	}
	
	@PostMapping("/verify")
	public ResponseEntity<?> VerifiedEmail(@RequestParam(name="email") String email){
		try {
			user_service.VerifyEmail(email);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
		return  ResponseEntity.ok().body("email verifed");
	}
  
    @PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody Credentials parametre){
    	try {
    		if(UserRepo.getUserByemail(parametre.getEmail())==null) {
    			return new ResponseEntity<String>("User Not Found",HttpStatus.CONFLICT);
    		}
    		Authentication authsuser = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(parametre.getEmail(), parametre.getPassword()));
    	}catch(BadCredentialsException e) {
    		return new ResponseEntity<String>("Incorrect email or password",HttpStatus.CONFLICT);
    	}
    	
        UserDetails user_detailts=userservice.loadUserByUsername(parametre.getEmail());
    	User user=UserRepo.getUserByemail(parametre.getEmail());
    	
    	if(user.getEmail_verified_at()==null) {
    		return new ResponseEntity<String>("Email not verified",HttpStatus.CONFLICT);
    	}
    	String token=jwtTokenUtil.generateToken(user_detailts);
    	Reponse data=new Reponse(user,token);
    	return  ResponseEntity.ok().body(data);
    }
    
    
    @PostMapping("/ForgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestParam(name="email")String email){
    	try {
    		user_service.forgotPassword(email);
    	}catch(Exception e) {
    		return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
    	}
    	return ResponseEntity.ok().body("Mail Send With Token");
    }
    
    
    @PostMapping("/ChangerPassword")
    public ResponseEntity<?> ResetPassword(@RequestBody ChangerPassword parametre){
    	String password_hash=securityConfig.passwordEncoder().encode(parametre.getPassword());
    	try {
    		user_service.ChangerPassword(parametre.getEmail(), parametre.getToken(), password_hash);
    	}catch(Exception e) {
    		return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
    	}
    	return ResponseEntity.ok().body("Password has been changed");
    }
    
}
=======
<<<<<<< HEAD
package app.project.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.project.SpringSecurity.SecurityConfig;
import app.project.SpringSecurity.UserDetailsImpl;
import app.project.entities.User;
import app.project.repository.UserRepository;
import app.project.service.UserService;
import authPrametre.Credentials;
import authPrametre.Reponse;
import app.project.jwt.jwtTokenUtil;
import app.project.mail.Mail;


@RestController
public class AuthController {
	
        
        @Autowired
	    AuthenticationManager authenticationManager; 
	 
	    @Autowired
	    UserRepository UserRepo;
	
	    @Autowired
		UserDetailsImpl userservice;
	    
	    @Autowired
	    UserService user_service;
	    
	    @Autowired
		private jwtTokenUtil  jwtTokenUtil;
	    
	    @Autowired
	    SecurityConfig securityConfig;
	    
	    @Autowired
	    Mail mailsender;

	@PostMapping("/SignUp")
	public ResponseEntity<?>  SignUp(@RequestBody User user){
		
		if(UserRepo.getUserByemail(user.getEmail())!=null) {
			return new ResponseEntity<String>("Email already Used",HttpStatus.CONFLICT);
		}
		
		if(UserRepo.getUserByCin(user.getCin())!=null) {
			return new ResponseEntity<String>("Cin already Used",HttpStatus.CONFLICT);
		}
		
		User new_user=new User();
		new_user.setBirth_day(user.getBirth_day());
		new_user.setCin(user.getCin());
		new_user.setEmail(user.getEmail());
		new_user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
		new_user.setFirstName(user.getFirstName());
		new_user.setLastName(user.getLastName());
		new_user.setNum_tlf(user.getNum_tlf());
		
		if(user.getPhoto()==null) {
			new_user.setPhoto(user.getFirstName().toUpperCase().charAt(0)+""+user.getLastName().toUpperCase().charAt(0));
		}else {
			new_user.setPhoto(user.getPhoto());
		}
		
		new_user.setRole(user.getRole());
		
		try {
			mailsender.sendVerificationEmail(user);
		}catch(MessagingException e) {
			return new ResponseEntity<String>("Error Connexion",HttpStatus.CONFLICT);
		}catch(UnsupportedEncodingException e) {
			return new ResponseEntity<String>("Unsupported Forme",HttpStatus.CONFLICT);
		}
	
		UserRepo.save(new_user);

		return  ResponseEntity.ok().body("user add");
	}
	
	@PostMapping("/verify")
	public ResponseEntity<?> VerifiedEmail(@RequestParam(name="email") String email){
		try {
			user_service.VerifyEmail(email);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
		return  ResponseEntity.ok().body("email verifed");
	}
  
    @PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody Credentials parametre){
    	try {
    		if(UserRepo.getUserByemail(parametre.getEmail())==null) {
    			return new ResponseEntity<String>("User Not Found",HttpStatus.CONFLICT);
    		}
    		Authentication authsuser = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(parametre.getEmail(), parametre.getPassword()));
    	}catch(BadCredentialsException e) {
    		return new ResponseEntity<String>("Incorrect email or password",HttpStatus.CONFLICT);
    	}
    	
        UserDetails user_detailts=userservice.loadUserByUsername(parametre.getEmail());
    	User user=UserRepo.getUserByemail(parametre.getEmail());
    	
    	if(user.getEmail_verified_at()==null) {
    		return new ResponseEntity<String>("Email not verified",HttpStatus.CONFLICT);
    	}
    	String token=jwtTokenUtil.generateToken(user_detailts);
    	Reponse data=new Reponse(user,token);
    	return  ResponseEntity.ok().body(data);
    }
}
=======
package app.project.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.project.SpringSecurity.SecurityConfig;
import app.project.SpringSecurity.UserDetailsImpl;
import app.project.entities.User;
import app.project.repository.UserRepository;
import app.project.service.UserService;
import authPrametre.Credentials;
import authPrametre.Reponse;
import app.project.jwt.jwtTokenUtil;
import app.project.mail.Mail;


@RestController
public class AuthController {
	
        
        @Autowired
	    AuthenticationManager authenticationManager; 
	 
	    @Autowired
	    UserRepository UserRepo;
	
	    @Autowired
		UserDetailsImpl userservice;
	    
	    @Autowired
	    UserService user_service;
	    
	    @Autowired
		private jwtTokenUtil  jwtTokenUtil;
	    
	    @Autowired
	    SecurityConfig securityConfig;
	    
	    @Autowired
	    Mail mailsender;

	@PostMapping("/SignUp")
	public ResponseEntity<?>  SignUp(@RequestBody User user){
		
		if(UserRepo.getUserByemail(user.getEmail())!=null) {
			return new ResponseEntity<String>("Email already Used",HttpStatus.CONFLICT);
		}
		
		if(UserRepo.getUserByCin(user.getCin())!=null) {
			return new ResponseEntity<String>("Cin already Used",HttpStatus.CONFLICT);
		}
		
		User new_user=new User();
		new_user.setBirth_day(user.getBirth_day());
		new_user.setCin(user.getCin());
		new_user.setEmail(user.getEmail());
		new_user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
		new_user.setFirstName(user.getFirstName());
		new_user.setLastName(user.getLastName());
		new_user.setNum_tlf(user.getNum_tlf());
		
		if(user.getPhoto()==null) {
			new_user.setPhoto(user.getFirstName().toUpperCase().charAt(0)+""+user.getLastName().toUpperCase().charAt(0));
		}else {
			new_user.setPhoto(user.getPhoto());
		}
		
		new_user.setRole(user.getRole());
		
		try {
			mailsender.sendVerificationEmail(user);
		}catch(MessagingException e) {
			return new ResponseEntity<String>("Error Connexion",HttpStatus.CONFLICT);
		}catch(UnsupportedEncodingException e) {
			return new ResponseEntity<String>("Unsupported Forme",HttpStatus.CONFLICT);
		}
	
		UserRepo.save(new_user);

		return  ResponseEntity.ok().body("user add");
	}
	
	@PostMapping("/verify")
	public ResponseEntity<?> VerifiedEmail(@RequestParam(name="email") String email){
		try {
			user_service.VerifyEmail(email);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
		}
		return  ResponseEntity.ok().body("email verifed");
	}
  
    @PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody Credentials parametre){
    	try {
    		if(UserRepo.getUserByemail(parametre.getEmail())==null) {
    			return new ResponseEntity<String>("User Not Found",HttpStatus.CONFLICT);
    		}
    		Authentication authsuser = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(parametre.getEmail(), parametre.getPassword()));
    	}catch(BadCredentialsException e) {
    		return new ResponseEntity<String>("Incorrect email or password",HttpStatus.CONFLICT);
    	}
    	
        UserDetails user_detailts=userservice.loadUserByUsername(parametre.getEmail());
    	User user=UserRepo.getUserByemail(parametre.getEmail());
    	
    	if(user.getEmail_verified_at()==null) {
    		return new ResponseEntity<String>("Email not verified",HttpStatus.CONFLICT);
    	}
    	String token=jwtTokenUtil.generateToken(user_detailts);
    	Reponse data=new Reponse(user,token);
    	return  ResponseEntity.ok().body(data);
    }
}
>>>>>>> c475a485596fd9a763dff634b70b71401dae7c73
>>>>>>> 38f55eb6285b997e2329447ba4a485796f3081a9
