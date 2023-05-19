package app.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.project.SpringSecurity.SecurityConfig;
import app.project.SpringSecurity.UserDetailsImpl;
import app.project.entities.Forum;
import app.project.entities.Inbox;
import app.project.entities.User;
import app.project.jwt.jwtTokenUtil;
import app.project.mail.Mail;
import app.project.repository.ClasseRepository;
import app.project.repository.ForumRepository;
import app.project.repository.InboxRepository;
import app.project.repository.NotificationRepository;
import app.project.repository.RoleRepository;
import app.project.repository.SpecialiteRepository;
import app.project.repository.UserDocumentRepostory;
import app.project.repository.UserRepository;
import app.project.repository.UserRoleRepository;
import app.project.service.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class ChatController {
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
	    UserDocumentRepostory userDocRepo;
	    
	    @Autowired
	    NotificationRepository NotifRepo;
	    
	    @Autowired
	    ForumRepository forumRepo;
	    
	    @Autowired
	    InboxRepository inboxRepo;
	    
	    
	    @PostMapping("/AddMessage")
	    public ResponseEntity<?> AddMessage(@RequestParam("message") String message,HttpServletRequest request){
	    	 User userAuth=user_service.UserAuth(request);
	    	 Forum chat=new Forum();
	    	 chat.setUserEnvoi(userAuth);
	    	 chat.setMessage(message);
	    	 forumRepo.save(chat);
	    	return ResponseEntity.ok("add message");
	    }
	    
	    @GetMapping("/GetAllMessages")
	    public ResponseEntity<?> GetAllMessages(){
	    	return ResponseEntity.ok(forumRepo.findAll());
	    }
	    
	    @GetMapping("/AllUserChat")
	    public ResponseEntity<?> AllUserChat(HttpServletRequest request){
	    	 User userAuth=user_service.UserAuth(request);
	    	 List<User> users=UserRepo.getUserChat(userAuth.getId());
	    	 return ResponseEntity.ok(users);
	    }
	    
	    @GetMapping("/AllChatPrivate")
	    public ResponseEntity<?> AllChatPrivate(@RequestParam("idrecu")long id,HttpServletRequest request){
	    	 User userAuth=user_service.UserAuth(request);
	    	 List<Inbox> chats=inboxRepo.getMessageprivate(userAuth.getId(), id);
	    	 return ResponseEntity.ok(chats);
	    }
	    
	    @GetMapping("/getUserByID")
	    public ResponseEntity<?> getUserById(@RequestParam("id") long id){
	    	 return ResponseEntity.ok(UserRepo.getUserById(id));
	    }
	    
	    @PostMapping("/AddMessagePrivate")
	    public ResponseEntity<?> AddMessage(@RequestParam("id")long user_id ,@RequestParam("message")String message,HttpServletRequest request){
	    	 User userAuth=user_service.UserAuth(request);
	    	 Inbox chat=new Inbox();
	    	 chat.setUserEnvoi(userAuth);
	    	 chat.setUserRecu(UserRepo.getUserById(user_id));
	    	 chat.setMessage(message);
	    	 inboxRepo.save(chat);
	    	return ResponseEntity.ok("add message");
	    }
	    
}
