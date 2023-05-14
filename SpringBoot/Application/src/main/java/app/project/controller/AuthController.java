package app.project.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.hibernate.annotations.CreationTimestamp;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.project.SpringSecurity.SecurityConfig;
import app.project.SpringSecurity.UserDetailsImpl;
import app.project.entities.Role;
import app.project.entities.RoleUser;
import app.project.entities.User;
import app.project.entities.classe;
import app.project.entities.specialite;
import app.project.repository.ClasseRepository;
import app.project.repository.RoleRepository;
import app.project.repository.SpecialiteRepository;
import app.project.repository.UserRepository;
import app.project.repository.UserRoleRepository;
import app.project.service.UserService;
import authPrametre.ChangerPassword;
import authPrametre.Credentials;
import authPrametre.MultipelDonne;
import authPrametre.Reponse;
import authPrametre.StatId;
import app.project.jwt.jwtTokenUtil;
import app.project.mail.Mail;

@CrossOrigin(origins = "http://localhost:8081")
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
	    
	    @Autowired
	    SpecialiteRepository SpecRepo;
	    
	    @Autowired
	    ClasseRepository ClasseRepo;
	    
	    @Autowired
	    RoleRepository rolerep;
	    
	    @Autowired
	    UserRoleRepository roleuser;
	    
	

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
			new_user.setSex(user.getSex());
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
			new_user.setClasse(user.getClasse());
		
			
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
    	if(UserRepo.GetStatus(user.getId())==0) {
    		return new ResponseEntity<String>("Your Acount Not Active",HttpStatus.CONFLICT);
    	}else if(UserRepo.GetStatus(user.getId())==2) {
    		return new ResponseEntity<String>("Your Acount Rejected",HttpStatus.CONFLICT);
    	}
    	String token=jwtTokenUtil.generateToken(user_detailts);
    	//Reponse data=new Reponse(user,token);
    	return  ResponseEntity.ok().body(new Reponse(user,token));
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
    
    @GetMapping("/getSpecialte")
    public List<specialite> getAllSpecialite(){
    	return SpecRepo.getAllSpec();
    }
    
    @GetMapping("/getClasse")
    public List<classe> getClasseById(@RequestParam("id") long id){
    	return ClasseRepo.getClasseByIdSpec(id);
    }
    
    @GetMapping("/getRoleByid")
	public Role finRolebyId(@RequestParam("id") long id){
		return rolerep.getRoleById(id);
	}
    
    @GetMapping("/GetAllRole")
   	public List<Role> FindAllRole(){
   		return rolerep.findAll();
   	}
    
    @GetMapping("/getClasseById")
   	public classe getClasse(@RequestParam("id") long id){
   		return ClasseRepo.getClasseById(id);
   	}
    
    @GetMapping("/ExistMail")
    public Boolean TestExitEmail(@RequestParam("email") String email) {
    	return user_service.getByEmail(email)==null ? true :false;
    }
    
    @GetMapping("/ExistToken")
    public Boolean TestExitCode(@RequestParam("code") String code) {
    	return UserRepo.CheckToken(code)==null ? true :false;
    }
   
    @PostMapping("/updateWelcome")
    public ResponseEntity<?> updateFiledWelcome(@RequestParam("email")String email){
    	User user=user_service.getByEmail(email);
    	user.setWelcome_field(true);
    	return ResponseEntity.ok().body("Password has been changed");
    }
    
    @PostMapping("/UpdateStatus")
    public ResponseEntity<?> UpdateStatus(@RequestBody StatId param){
    	RoleUser user=	roleuser.getUserRole(param.getId());
    	user.setStatus(param.getStatus());
    	roleuser.save(user);
    	return ResponseEntity.ok().body("Status Changed");
    }
    
    @GetMapping("/getUsers")
    public ResponseEntity<?> getUsers(){
    		List<Object[]> rows = UserRepo.getAllUsersWithStatus();
    	    List<MultipelDonne> users = new ArrayList<>();

    	    for (Object[] row : rows) {
    	    	 if(  user_service.TestRoleName((String)row[7])==false ) {
    	    		  	 MultipelDonne user = new MultipelDonne();
    	    	         user.setId((BigInteger) row[0]);
    	    	         user.setFirstName((String) row[3]);
    	    	         user.setLastname((String)row[4]);
    	    	         user.setEmail((String)row[7]);
    	    	         user.setPhoto((String)row[5]);
    	    	         user.setCreated_at((Timestamp)row[6]);
    	    	         user.setRoleName(UserRepo.GetRoleByIdUser((BigInteger) row[0]));
    	    	         user.setStatus((int) row[row.length-1]);
    	    	         users.add(user);
    	    	}
    	       
    	    }

    	    return ResponseEntity.ok().body(users);

    }
    
}
