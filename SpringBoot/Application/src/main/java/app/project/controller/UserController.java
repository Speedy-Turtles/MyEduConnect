package app.project.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.project.SpringSecurity.SecurityConfig;
import app.project.SpringSecurity.UserDetailsImpl;
import app.project.entities.User;
import app.project.jwt.jwtTokenUtil;
import app.project.mail.Mail;
import app.project.repository.ClasseRepository;
import app.project.repository.NotificationRepository;
import app.project.repository.RoleRepository;
import app.project.repository.SpecialiteRepository;
import app.project.repository.UserRepository;
import app.project.repository.UserRoleRepository;
import app.project.service.UserService;
import authPrametre.InfoEmail;
import authPrametre.InfoPersonnel;
import authPrametre.OldPassword;
import authPrametre.UploadPhoto;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class UserController {
	
	 @Autowired
	    AuthenticationManager authenticationManager; 
	 
	    @Autowired
	    UserRepository UserRepo;
	    
	    @Autowired
	    NotificationRepository NotifRepo;
	
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
	    
	    @Autowired
	    SpecialiteRepository SpecRepo;
	    
	    @Autowired
	    ClasseRepository ClasseRepo;
	    
	    @Autowired
	    RoleRepository rolerep;
	    
	    @Autowired
	    UserRoleRepository roleuser;
	    
	    
	    @PostMapping("/uploadPhoto")
	    public ResponseEntity<?> EditPhoto(@RequestBody UploadPhoto photo,HttpServletRequest request){
	    	  User userAuth=user_service.UserAuth(request);
	    	  userAuth.setPhoto(photo.getPhoto());
	    	  UserRepo.save(userAuth);
	    	  return  ResponseEntity.ok().body("Photo Upload");
	    }
	    
	    
	    @PostMapping("/EditInfoPersonnel")
	    public ResponseEntity<?> EditInfoPersonnel(@RequestBody InfoPersonnel data,HttpServletRequest request){
	    	  User userAuth=user_service.UserAuth(request);
	    	  userAuth.setCin(data.getCin());
	    	  userAuth.setFirstName(data.getFirstname());
	    	  userAuth.setLastName(data.getLastname());
	    	  userAuth.setNum_tlf(data.getNum_tlf());
	    	  userAuth.setBirth_day(data.getBirth_day());
	    	  UserRepo.save(userAuth);
	    	  return  ResponseEntity.ok().body("User Modified");
	    }
	    
	    @GetMapping("/CheckPassword")
	    public  ResponseEntity<?> CheckPassword(@RequestParam("password") String pass,HttpServletRequest request) {
	    	   User user = user_service.UserAuth(request);
	    	    if (user == null) {
	    	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
	    	    }
	    	    boolean res = securityConfig.passwordEncoder().matches(pass, user.getPassword());
	    	    if (res) {
	    	        return ResponseEntity.ok().body(true);
	    	    } else {
	    	        return ResponseEntity.ok().body(false);
	    	    }
	    }
	    
	    @PostMapping("/ChangerPasswordActuel")
	    public ResponseEntity<?> changerPassword(@RequestParam("password") String pass,HttpServletRequest request){
	    	   User user = user_service.UserAuth(request);
	    	   if (user == null) {
	    	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
	    	    }
	    	   user.setPassword(securityConfig.passwordEncoder().encode(pass));
	    	   UserRepo.save(user);
	    	   return  ResponseEntity.ok().body("Password Modified");
	    }
	    
	    @GetMapping("/SendChangedEmail")
	    public ResponseEntity<?> SendChangedEmail(@RequestParam("email") String email_new,HttpServletRequest request){
	    	    User user = user_service.UserAuth(request);
	    	    if (user == null) {
	    	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
	    	    }
	    	    try {
	    	    	 mailsender.SendMailConfirm(user.getEmail(),email_new);
	    		}catch(MessagingException e) {
					return new ResponseEntity<String>("Error Connexion",HttpStatus.CONFLICT);
				}catch(UnsupportedEncodingException e) {
					return new ResponseEntity<String>("Unsupported Forme",HttpStatus.CONFLICT);
				}

	    	    return  ResponseEntity.ok().body("Send Mail For Confirm");
	    }
	    
	    @PostMapping("/updateEmail")
	    public ResponseEntity<?> updateEmail(@RequestBody InfoEmail data){
	    	   User user = user_service.getByEmail(data.getEmail_old());
	    	   if (user == null) {
	    	        return ResponseEntity.status(HttpStatus.CONFLICT).body("User not Found");
	    	    }
	    	   user.setEmail(data.getEmail_new());
	    	   user.setEmail_verified_at(null);
	    	   UserRepo.save(user);
	    	   return  ResponseEntity.ok().body("Email Modified");
	    }
	    
	    
	    @GetMapping("/getUserMan")
	    	public ResponseEntity<?> getUserMan(){
	    		if(user_service.getUserMan()==0) {
	    			return new ResponseEntity<String>("No man user found",HttpStatus.NOT_FOUND);
	    			
	    		}else{
	    			return  ResponseEntity.ok().body(user_service.getUserMan());
	    			}
	    	}
	    
	    @GetMapping("/getUserWomen")
    	public ResponseEntity<?> getUserWomen(){
    		if(user_service.getUserWomen()==0) {
    			return new ResponseEntity<String>("No Women user found",HttpStatus.NOT_FOUND);
    			
    		}else{
    			return  ResponseEntity.ok().body(user_service.getUserWomen());
    			}
    	}
	    
	    @GetMapping("/getUserEnseignant")
    	public ResponseEntity<?> getUserEnseignant(){
    		if(user_service.getUserWomen()==0) {
    			return new ResponseEntity<String>("No Enseignant user found",HttpStatus.NOT_FOUND);
    			
    		}else{
    			return  ResponseEntity.ok().body(user_service.getUserEnseignant());
    			}
    	}
	    
	    @GetMapping("/getUserEtudiant")
    	public ResponseEntity<?> getUserEtudiant(){
    		if(user_service.getUserWomen()==0) {
    			return new ResponseEntity<String>("No Etudiant user found",HttpStatus.NOT_FOUND);
    			
    		}else{
    			return  ResponseEntity.ok().body(user_service.getUserEnseignant());
    			}
    	}
	    
	    
 }
	    


