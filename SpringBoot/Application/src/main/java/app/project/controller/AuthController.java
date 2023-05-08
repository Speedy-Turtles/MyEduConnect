package app.project.controller;

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
import org.springframework.web.bind.annotation.RestController;
import app.project.SpringSecurity.UserDetailsImpl;
import app.project.entities.User;
import app.project.repository.UserRepository;
import app.project.service.UserService;
import authPrametre.Credentials;
import authPrametre.Reponse;
import app.project.jwt.jwtTokenUtil;


@RestController
public class ProjectController {
	
    
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
    	String token=jwtTokenUtil.generateToken(user_detailts);
    	Reponse data=new Reponse(user,token);
    	return  ResponseEntity.ok().body(data);
    }
}
