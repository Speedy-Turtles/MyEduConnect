package app.project.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.project.SpringSecurity.SecurityConfig;
import app.project.SpringSecurity.UserDetailsImpl;
import app.project.entities.Notification;
import app.project.entities.User;
import app.project.jwt.jwtTokenUtil;
import app.project.mail.Mail;
import app.project.repository.ClasseRepository;
import app.project.repository.RoleRepository;
import app.project.repository.SpecialiteRepository;
import app.project.repository.UserRepository;
import app.project.repository.UserRoleRepository;
import app.project.service.NotificationService;
import app.project.service.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class NotificationController {
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
    
    @Autowired
    SpecialiteRepository SpecRepo;
    
    @Autowired
    ClasseRepository ClasseRepo;
    
    @Autowired
    RoleRepository rolerep;
    
    @Autowired
    UserRoleRepository roleuser;
    
    @Autowired
    NotificationService notifServc;

    @GetMapping("/GetNotif")
    public ResponseEntity<?> getallNotif(HttpServletRequest request){
    	  User userAuth=user_service.UserAuth(request);
    	  List<Notification> notif=notifServc.getNotif(userAuth.getId());
    	  return  ResponseEntity.ok().body(notif);
    }
    
    @GetMapping("/GetNotifNotSeen")
    public ResponseEntity<?> GetNotifNotSeen(HttpServletRequest request){
    	  User userAuth=user_service.UserAuth(request);
    	  List<Notification> notif=notifServc.getNotifNotSeenyet(userAuth.getId());
    	  return  ResponseEntity.ok().body(notif);
    }
    
    @DeleteMapping("/DeleteNotificationById")
    public ResponseEntity<?> DeleteNotifById(@RequestParam("id") long id){
    	 notifServc.DeleteById(id);
    	 return  ResponseEntity.ok().body("delete with success");
    }
    
    @DeleteMapping("/DeleteAllNotif")
    public ResponseEntity<?> DeleteAll(HttpServletRequest request){
    	  User userAuth=user_service.UserAuth(request);
    	  notifServc.DeleteAllNotif(userAuth.getId());
    	 return  ResponseEntity.ok().body("Delete success");
    }
    
    @PostMapping("/updateNotif")
    public ResponseEntity<?> updateNotif(HttpServletRequest request){
    	  User userAuth=user_service.UserAuth(request);
    	  notifServc.updateAllNotifUser(userAuth.getId());
    	  return  ResponseEntity.ok().body("update Notif With Success");
    }
    
}
