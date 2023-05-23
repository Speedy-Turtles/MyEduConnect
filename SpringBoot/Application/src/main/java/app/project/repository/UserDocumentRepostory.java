package app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestParam;

import app.project.entities.EmbedIdDocument;
import app.project.entities.UserDocument;

@EnableJpaRepositories
public interface UserDocumentRepostory extends JpaRepository<UserDocument, EmbedIdDocument> {
	
	List<UserDocument> findAll();
	
	@Query(value="SELECT * FROM `user_document` WHERE document_id=? and user_id=?",nativeQuery=true)
	UserDocument docByUserDoc(long id,long iddoc);
	
	/*@Query(value="Delete From `user_document` WHERE document_id=? and user_id=?",nativeQuery=true)
	void deleteUserDoc(long id,long iddoc);*/
		
}
