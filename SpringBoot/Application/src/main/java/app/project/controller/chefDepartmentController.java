package app.project.controller;


import java.util.ArrayList;
import java.util.List;

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
import app.project.entities.EmbedIdDocument;
import app.project.entities.Notification;
import app.project.entities.Role;
import app.project.entities.User;
import app.project.entities.UserDocument;
import app.project.entities.classe;
import app.project.entities.specialite;
import app.project.jwt.jwtTokenUtil;
import app.project.mail.Mail;
import app.project.repository.ClasseRepository;
import app.project.repository.NotificationRepository;
import app.project.repository.RoleRepository;
import app.project.repository.SpecialiteRepository;
import app.project.repository.UserDocumentRepostory;
import app.project.repository.UserRepository;
import app.project.repository.UserRoleRepository;
import app.project.service.UserService;
import authPrametre.DataClasse;
import authPrametre.DataSpecialite;
import authPrametre.InfoClasse;
import authPrametre.SpecDonne;
import net.minidev.json.JSONObject;

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
    
    @Autowired
    UserDocumentRepostory userDocRepo;
    
    @Autowired
    NotificationRepository NotifRepo;
    
    
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
    
    @GetMapping("/getAllClasse")
    public ResponseEntity<?> getClasse(
    		@RequestParam(name="id",defaultValue = "0") long id,
    		@RequestParam(name = "search",defaultValue = "") String nom,
    		@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "per_page", defaultValue = "2") int size){
    	
    	Page<classe> classe = null;
    	if(nom.isEmpty()) {
        	classe = ClasseRepo.findAll( PageRequest.of(page, size));
    	}
    	
    	if(id!=0) {
    		classe =ClasseRepo.getClasseByIdSpecPagination(id,PageRequest.of(page, size));
    	}
    	
    	if(nom.isEmpty()==false) {
    		classe = ClasseRepo.findByNom(nom, PageRequest.of(page, size));
    	}

    	
    	int totale = classe.getTotalPages();
		int[] count_page = new int[totale];
		for (int i = 0; i < totale; i++) {
			count_page[i] = i;
		}

		DataClasse data=new DataClasse(count_page,classe,page);
		return ResponseEntity.ok(data);

    }
    
    @DeleteMapping("/deleteSpec")
    public ResponseEntity<?> deleteSpec(@RequestParam("id")long id){
    	List<classe> classes=ClasseRepo.getClasseByIdSpec(id);
    	int VerifyClasse=0;
    	if(classes.size()==0) {
    		SpecRepo.deleteById(id);
    		return ResponseEntity.ok(true);
    	}else {
    		for(classe c:classes) {
    			List<User> users=UserRepo.GetUserByClass(c.getIdclasse());
    			if(users.size()==0) {
    				VerifyClasse++;
    			}
    		}
    		if(classes.size()==VerifyClasse) {
    			SpecRepo.deleteById(id);
    			return ResponseEntity.ok(true);
    		}else {
    			return ResponseEntity.ok(false);
    		}
    	}
    }
    
    @DeleteMapping("/deleteClasse")
    public ResponseEntity<?> deleteClasse(@RequestParam("id")long id){
    	classe classe=ClasseRepo.getClasseById(id);
    	if(classe==null) {
    		return ResponseEntity.ok("Not Found");
    	}else {
    		List<User> users=UserRepo.GetUserByClass(classe.getIdclasse());
    	    if(users.size()==0) {
    	    	ClasseRepo.deleteById(id);
    			return ResponseEntity.ok(true);
    	    }else {
    	    	return ResponseEntity.ok(false);
    	    }
    	}
    	
    }
    
    @PostMapping("/ChangerClasse")
    public ResponseEntity<?> ChangerClasse(@RequestParam("id")long id,@RequestParam("new_id") long idnew){
    		classe classe=ClasseRepo.getClasseById(id);
    		if(classe==null) {
        		return ResponseEntity.ok("Not Found");
        	}else {
			List<User> users=UserRepo.GetUserByClass(classe.getIdclasse());
			 for(User u:users) {
				 u.setClasse(null);
				 u.setClasse(ClasseRepo.getClasseById(idnew));
			 }
			 	ClasseRepo.deleteById(id);
    			return ResponseEntity.ok("Classe add to user");
        	}
    }
    
    @PostMapping("/UpdateClasse")
    public ResponseEntity<?> updateClasse(@RequestParam("id") long id,@RequestParam("nom") String nom){
    	classe classe=ClasseRepo.getClasseById(id);
    	classe.setNom(nom);
    	ClasseRepo.save(classe);
    	return ResponseEntity.ok("Update With Success");
    }
    
    @PostMapping("/ChangerSpecialite")
    public ResponseEntity<?> changerSpec(@RequestParam("id")long id,@RequestParam("new_id") long idnew){
    	List<classe> classes=ClasseRepo.getClasseByIdSpec(id);
    	for(classe c:classes) {
			List<User> users=UserRepo.GetUserByClass(c.getIdclasse());
			 for(User u:users) {
				 u.setClasse(null);
				 u.setClasse(ClasseRepo.getClasseById(idnew));
			 }
		}
    	SpecRepo.deleteById(id);
    	return ResponseEntity.ok("Classe add to user");
    }
    
    @GetMapping("/GetAllSpec")
    public ResponseEntity<?> GetAllSpec(){
    	return ResponseEntity.ok(SpecRepo.findAll());
    }
    
    @GetMapping("/GetClasse")
    public ResponseEntity<?> getClasseById(@RequestParam("id")long id){
    	List<classe> classes=ClasseRepo.getClasseByIdSpec(id);
    	return ResponseEntity.ok(classes);
    }
    
    @PostMapping("/updateSpecilte")
    public ResponseEntity<?> updateSpec(@RequestBody SpecDonne spec){
    	specialite specialite=SpecRepo.getSpecById(spec.getId());
    	specialite.setNiveau(spec.getNiveau());
    	specialite.setType(spec.getType());
    	SpecRepo.save(specialite);
    	return ResponseEntity.ok("Update  with success");
    }
    
    @PostMapping("/AddSpec")
    public ResponseEntity<?> Add(@RequestBody SpecDonne spec){
    	specialite specialite=new specialite();
    	specialite.setNiveau(spec.getNiveau());
    	specialite.setType(spec.getType());
    	SpecRepo.save(specialite);
    	return ResponseEntity.ok("Added  with success");
    }
    
    @PostMapping("/AddClasse")
    public ResponseEntity<?> AddClass(@RequestBody InfoClasse classe){
    	classe ClasseAdded=new classe();
    	ClasseAdded.setNom(classe.getName());
    	specialite specialite=SpecRepo.getSpecById(classe.getId());
    	ClasseAdded.setSpec(specialite);
    	ClasseRepo.save(ClasseAdded);
    	return ResponseEntity.ok("Classe Added");
    }
    
    
    @GetMapping("/getDoc")
    public ResponseEntity<?> getAllDocuments(@RequestParam(name="nom",defaultValue = "")String nom){
    	List<UserDocument> Accepted=new ArrayList<UserDocument>();
    	List<UserDocument> Rejeter=new ArrayList<UserDocument>();;
    	List<UserDocument> Waiting=new ArrayList<UserDocument>();;
    	
    	List<UserDocument> AllDoc=userDocRepo.findAll();
    	
    	for(UserDocument doc:AllDoc) {
    		if(doc.getEtat()==0) {
    			Waiting.add(doc);
    		}else if(doc.getEtat()==1) {
    			Accepted.add(doc);
    		}else {
    			Rejeter.add(doc);
    		}
    	}
    	JSONObject res=new JSONObject();
    	res.appendField("accepted", Accepted);
    	res.appendField("waiting", Waiting);
    	res.appendField("Rejeter", Rejeter);
    	//for(int i=0;i<AllDoc.size();i++) {}
    	return ResponseEntity.ok(res);
    }
    
    @PostMapping("/ChangerEtat")
    public ResponseEntity<?> ChangerEtatDoc(@RequestParam("id") long iddoc,@RequestParam("idUser")long idUser,@RequestParam("etat") int etat){
    	
    	UserDocument doc=userDocRepo.docByUserDoc(iddoc,idUser);
    	
    	if(doc==null) {
    		return ResponseEntity.ok("No Trouve");
    	}else {
    		doc.setEtat(etat);
    		if(etat==1) {
    			doc.setNombre(doc.getNombre()+1);
    		}
    		userDocRepo.save(doc);
    		User chef=UserRepo.GetChefDepartment();
    		Notification notif=new Notification();
			notif.setUserEnvoi(chef);
			notif.setEtat(0);
			notif.setUserRecu(doc.getUser());
			if(etat==1) {
				notif.setMessage("Votre Deamande Sur "+doc.getDocument().getType()+" a été accepté");
			}else if(etat==2) {
				notif.setMessage("Votre Deamande Sur "+doc.getDocument().getType()+" a été rejeté");
			}
			NotifRepo.save(notif);
    		return ResponseEntity.ok("Document Etat Changed");
    	}
    }
    
    @DeleteMapping("/DeleteUserDoc")
    public ResponseEntity<?> deleteByIdUserDoc(@RequestParam("id") long iddoc,@RequestParam("idUser")long idUser){
    	UserDocument doc=userDocRepo.docByUserDoc(iddoc,idUser);
    	if(doc==null) {
    		return ResponseEntity.ok("No Trouve");
    	}else {
    		//userDocRepo.deleteUserDoc(iddoc,idUser);
    		userDocRepo.delete(doc);
    		return ResponseEntity.ok("Delete With success");
    	}
    }
    
    
}
