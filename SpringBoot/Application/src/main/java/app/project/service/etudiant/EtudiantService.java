package app.project.service.etudiant;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import java.lang.annotation.Documented;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import app.project.entities.Document;
import app.project.entities.EmbedIdDocument;
import app.project.entities.User;
import app.project.entities.UserDocument;
import app.project.repository.DemandeRepository;
import app.project.repository.DocumentRepository;
import app.project.repository.UserDocumentRepostory;
import app.project.service.UserService;

@Service
public class EtudiantService {
	
	@Autowired
	DocumentRepository documentRepository;
	
	@Autowired
	UserDocumentRepostory userDocumentRepostory;
	
	@Autowired
	UserService userService;
	
	@Autowired
	DemandeRepository demandeRepository;
	
	public List<Document> getAllDocuments() {	
		return documentRepository.findAll();
	}
	
	public List<UserDocument> getAllDemandes() {	
		return demandeRepository.findAll();
	}
	
	public Boolean demandeCheck(HttpServletRequest request,long document_id) {
		 User user = userService.UserAuth(request);
		 long user_id = user.getId();
		 return demandeRepository.getDemandeById(document_id,user_id)==null ? true : false;
		 
	}
	
	public Document getDocumentById(long id) {
		return documentRepository.getdocbyId(id);
	}
	
	public UserDocument getAllDemandesUser(HttpServletRequest request){
		 User user = userService.UserAuth(request);
		 long user_id = user.getId();
		if(demandeRepository.getDemandeByUser(user_id)==null) {
			new ResponseEntity<String>("No demande Found",HttpStatus.NOT_FOUND);
		}
		return demandeRepository.getDemandeByUser(user_id);
	}
	
	
	

}
