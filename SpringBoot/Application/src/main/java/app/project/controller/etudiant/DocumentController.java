package app.project.controller.etudiant;




import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import app.project.SpringSecurity.SecurityConfig;
import app.project.entities.Document;
import app.project.entities.EmbedIdDocument;
import app.project.entities.Notification;
import app.project.entities.User;
import app.project.entities.UserDocument;
import app.project.repository.NotificationRepository;
import app.project.repository.UserDocumentRepostory;
import app.project.repository.UserRepository;
import app.project.service.UserService;
import app.project.service.etudiant.EtudiantService;
import authPrametre.Reponse;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class DocumentController {
	
	@Autowired
	SecurityConfig security;
	
	@Autowired
	EtudiantService etudiantService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserDocumentRepostory userDocumentRepository;
	
	@Autowired
	NotificationRepository notificationRepository;
	
	@GetMapping("/getDocuments")
	public ResponseEntity<?> Documents(){
		
		if(etudiantService.getAllDocuments().size()==0) {
			return  new ResponseEntity<String>("No documents found",HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok().body(etudiantService.getAllDocuments());
	}
		
	@GetMapping("/getDemandes")
	public ResponseEntity<?> getDemandes(){
		
		if(etudiantService.getAllDemandes().size()==0) {
			return  new ResponseEntity<String>("No demandes found",HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok().body(etudiantService.getAllDemandes());
	}
	
	
	@GetMapping("/accepterDemande")
	public ResponseEntity<?> accepterDemande(HttpServletRequest request,@RequestParam("id")long id){
		UserDocument demande_check=etudiantService.demandeCheck(request, id);
		if(demande_check==null) {
			return  new ResponseEntity<String>("No demande found",HttpStatus.NOT_FOUND);
		}else {
			demande_check.setEtat(1);
			demande_check.setNombre(demande_check.getNombre()+1);
		}
		return new ResponseEntity<String>("data updated succesfully",HttpStatus.FOUND);
	}
	
	
	
	@PostMapping("/addDemande")
	public ResponseEntity<?> addDemande(HttpServletRequest request,@RequestParam("id")long id,@RequestParam("langue")String langue){
		UserDocument demande_check=etudiantService.demandeCheck(request, id);
		User chefDepartement = userRepository.GetChefDepartment();
		User user =userService.UserAuth(request);
		Document document = etudiantService.getDocumentById(id);
		Notification notification = new Notification();
		notification.setUserEnvoi(user);
		notification.setUserRecu(chefDepartement);
		notification.setMessage(user.getFirstName()+" " + user.getLastName() + " demande document "+document.getType());
		notification.setEtat(0);
		notificationRepository.save(notification);
		
		if(demande_check!=null) {
			demande_check.setEtat(0);
			demande_check.setLangue(langue);
			userDocumentRepository.save(demande_check);
			return  new ResponseEntity<String>("Demande updated successfuly",HttpStatus.OK);
		}else {
			UserDocument demande = new UserDocument();
			demande.setUser(user);
			demande.setDocument(document);
			demande.setEtat(0);
			demande.setNombre(0);
			demande.setLangue(langue);
			userDocumentRepository.save(demande);
			return  new ResponseEntity<String>("Demande create successfuly",HttpStatus.CREATED);
		}
	}
	
	
	@GetMapping("/initailiserDemande")
	public UserDocument initailiser_demande(HttpServletRequest request,@RequestParam("id")long id){
		UserDocument demande_check=etudiantService.demandeCheck(request, id);
		demande_check.setEtat(3);
		demande_check.setLangue("francais");
		userDocumentRepository.save(demande_check);
		return  demande_check;
	}
	
	@GetMapping("/getDemande")
	public ResponseEntity<?> getDemandeById(HttpServletRequest request,@RequestParam("id")long id) {
		UserDocument demande=etudiantService.demandeCheck(request, id);
		if(demande==null){
			return  new ResponseEntity<String>("There is no demande",HttpStatus.NOT_FOUND);
		}
		
			return ResponseEntity.ok().body(etudiantService.getAllDemandes());
		
	}
	
	
	@GetMapping("/getAllDemandes")
		public UserDocument getAllDemandesUser(HttpServletRequest request) {
		return etudiantService.getAllDemandesUser(request);
		}
	
	@PutMapping("/updateDemande")
	public ResponseEntity<?> deleteDemande(HttpServletRequest request){
		UserDocument demande=etudiantService.getAllDemandesUser(request);
		demande.setEtat(-1);
		userDocumentRepository.save(demande);
		return  new ResponseEntity<String>("There is no demande",HttpStatus.OK);
	}
	}


	
	

	


