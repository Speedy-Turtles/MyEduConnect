package app.project.controller;

import org.apache.logging.log4j.message.ObjectArrayMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import app.project.SpringSecurity.SecurityConfig;
import app.project.SpringSecurity.UserDetailsImpl;
import app.project.entities.specialite;
import app.project.jwt.jwtTokenUtil;
import app.project.mail.Mail;
import app.project.repository.ClasseRepository;
import app.project.repository.RoleRepository;
import app.project.repository.SpecialiteRepository;
import app.project.repository.UserRepository;
import app.project.repository.UserRoleRepository;
import app.project.service.UserService;
import authPrametre.DataSpecialite;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class chefDepartmentController {
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
    
    
    @GetMapping("/getSepecialite")
    public ResponseEntity<?> getSpecialite(
    		@RequestParam(name = "search",defaultValue = "") String nom,
    		@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "per_page", defaultValue = "2") int size){
    	
    	Page<specialite> spec;
    	if(nom.isEmpty()) {
        	spec = SpecRepo.findAll( PageRequest.of(page, size));
    	}else {
        	spec = SpecRepo.findByType(nom, PageRequest.of(page, size));
    	}
    	
    	int totale = spec.getTotalPages();
		int[] count_page = new int[totale];
		for (int i = 0; i < totale; i++) {
			count_page[i] = i;
		}

		DataSpecialite data=new DataSpecialite(count_page,spec,page);
		return ResponseEntity.ok(data);
		
    }
}
