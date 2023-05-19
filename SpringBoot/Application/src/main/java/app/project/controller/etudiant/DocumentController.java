package app.project.controller.etudiant;




import java.util.ArrayList;
import java.util.List;

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
import app.project.repository.DocumentRepository;
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
	
	@Autowired
	DocumentRepository docRepo;
	
	@GetMapping("/getDocuments")
	public ResponseEntity<?> Documents(){
		
		if(etudiantService.getAllDocuments().size()==0) {
			return  new ResponseEntity<String>("No documents found",HttpStatus.OK);
		}
		return ResponseEntity.ok().body(etudiantService.getAllDocuments());
	}
		
	@GetMapping("/getDemandes")
	public ResponseEntity<?> getDemandes(){
		
		if(etudiantService.getAllDemandes().size()==0) {
			return  new ResponseEntity<String>("No demandes found",HttpStatus.OK);
		}
		return ResponseEntity.ok().body(etudiantService.getAllDemandes());
	}
	

	
	
	

	@PostMapping("/addDemande")
	public ResponseEntity<?> addDemande(HttpServletRequest request,@RequestParam("id")long id,@RequestParam("langue")String langue){
			User user =userService.UserAuth(request);
			Document document = etudiantService.getDocumentById(id);
			User chefDepartement = userRepository.GetChefDepartment();
			Notification notification = new Notification();
			notification.setUserEnvoi(user);
			notification.setUserRecu(chefDepartement);
			notification.setMessage(user.getFirstName()+" " + user.getLastName() + " demande document "+document.getType());
			notification.setEtat(0);
			notificationRepository.save(notification);
			UserDocument demande=userDocumentRepository.docByUserDoc(document.getId(),user.getId());
			/*List<User> users=new ArrayList<>();
			users.add(user);
			document.setUser(users);
			docRepo.save(document);*/
			if(demande==null) {
				UserDocument demande_new = new UserDocument();
				demande_new.setUser(user);
				demande_new.setDocument(document);
				demande_new.setEtat(0);
				demande_new.setNombre(0);
				demande_new.setLangue(langue);
				userDocumentRepository.save(demande_new);
				return  new ResponseEntity<String>("1",HttpStatus.CREATED);
			}else {
				demande.setNombre(demande.getNombre()+1);
				demande.setLangue(langue);
				userDocumentRepository.save(demande);
				return  new ResponseEntity<String>("2",HttpStatus.CREATED); 
			}
			//return  new ResponseEntity<String>("test",HttpStatus.CREATED); 
	}
	

	
	@GetMapping("/getDemande")
	public ResponseEntity<?> getDemandeById(HttpServletRequest request,@RequestParam("id")long id) {

		Boolean test=etudiantService.demandeCheck(request, id);
		if(test==false){
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


	
	

	


