package app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.project.entities.EmbedIdDocument;
import app.project.entities.RoleUser;
import app.project.entities.UserDocument;

public interface DemandeRepository extends JpaRepository<UserDocument, EmbedIdDocument>{
	
	@Query(value="select * from user_document where document_id=:document_id and user_id=:user_id",nativeQuery=true)
	UserDocument getDemandeById(long document_id,long user_id);
	
	@Query(value="select * from user_document where  user_id=:user_id",nativeQuery=true)
    UserDocument getDemandeByUser(long user_id);
}
