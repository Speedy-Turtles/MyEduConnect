package app.project.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.sun.mail.iap.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.project.SpringSecurity.SecurityConfig;
import app.project.SpringSecurity.UserDetailsImpl;
import app.project.entities.Notification;
import app.project.entities.Role;
import app.project.entities.RoleUser;
import app.project.entities.User;
import app.project.entities.classe;
import app.project.entities.specialite;
import app.project.jwt.jwtTokenUtil;
import app.project.mail.Mail;
import app.project.repository.ClasseRepository;
import app.project.repository.NotificationRepository;
import app.project.repository.RoleRepository;
import app.project.repository.SpecialiteRepository;
import app.project.repository.UserRepository;
import app.project.repository.UserRoleRepository;
import app.project.service.UserService;
import authPrametre.DataClasse;
import authPrametre.DataSpecialite;
import authPrametre.InfoClasse;
import authPrametre.SpecDonne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class Admincontroller {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository UserRepo;

	@Autowired
	UserDetailsImpl userservice;

	@Autowired
	UserService user_service;

	@Autowired
	private jwtTokenUtil jwtTokenUtil;

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
	NotificationRepository notifrep;

	@GetMapping("/enseignats/all")
	public ResponseEntity<?> getEnseignats() {
		List<User> proffesors = UserRepo.getProffesors();
		return ResponseEntity.ok().body(proffesors);
	}

	@GetMapping("/getChef")
	public ResponseEntity<?> getChef() {
		User chefDep = UserRepo.GetChefDepartment();
		return ResponseEntity.ok().body(chefDep);
	}

	@DeleteMapping("/deleteChef")
	public ResponseEntity<?> DeleteChefDep(@RequestParam(name = "user_id") long id_user) {
		User currentChefDep = UserRepo.GetChefDepartment();
		long id_role = currentChefDep.getRole().get(1).getId();
		roleuser.deleteChefDep(currentChefDep.getId(), id_role);
		return ResponseEntity.ok().body("chef departement deleted succesfully");
	}

	@PostMapping("/changeChef")
	public ResponseEntity<?> AddChefDep(@RequestParam(name = "user_id") long id_user, HttpServletRequest request) {
		User userAuth = user_service.UserAuth(request);
		User currentChefDep = UserRepo.GetChefDepartment();
		User neauvChef = UserRepo.getUserById(id_user);
		Role chefRole = rolerep.findByRoleName("chefDepartment");
		if (chefRole == null) {
			chefRole = new Role();
			chefRole.setRoleName("chefDepartment");
			chefRole = rolerep.save(chefRole);
		}

		if (currentChefDep != null) {
			roleuser.deleteChefDep(currentChefDep.getId(), chefRole.getId());
		}
		List<Role> roleList = new ArrayList<Role>();
		roleList.add(chefRole);
		roleList.add(neauvChef.getRole().get(0));
		neauvChef.setRole(roleList);
		Notification notif = new Notification();
		notif.setUserEnvoi(userAuth);
		notif.setEtat(0);
		notif.setMessage("you have been named the new chef department by : " + userAuth.getFirstName() + " "+ userAuth.getLastName());
		notif.setUserRecu(neauvChef);
		notifrep.save(notif);
		return ResponseEntity.ok().body("Chef department changed succesfully");
	}
}
