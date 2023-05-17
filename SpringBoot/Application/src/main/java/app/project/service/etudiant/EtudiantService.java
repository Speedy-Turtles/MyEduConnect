package app.project.service.etudiant;

import java.lang.annotation.Documented;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.project.entities.Document;
import app.project.entities.User;
import app.project.entities.UserDocument;
import app.project.repository.DocumentRepository;
import app.project.repository.UserDocumentRepostory;
import app.project.service.UserService;

@Service
public class EtudiantService {
	
	@Autowired
	DocumentRepository documentRepository;
	
	@Autowired
	UserDocumentRepostory userDocumentRepostory;
	
	
	public List<Document> getAllDocuments() {	
		return documentRepository.findAll();
	}
	
	
	
	

}
