package app.project.controller.etudiant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.project.entities.User;
import app.project.service.UserService;
import app.project.service.etudiant.EtudiantService;
import authPrametre.Reponse;

@RestController
public class DocumentController {
	
	@Autowired
	EtudiantService etudiantService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/getDocuments")
	public ResponseEntity<?> Documents(){
		
		if(etudiantService.getAllDocuments().size()==0) {
			return  new ResponseEntity<String>("No documents found",HttpStatus.OK);
		}
		return ResponseEntity.ok().body(etudiantService.getAllDocuments());
	}
	

}
