package app.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.project.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
 @Query(value="select * from document where id=:id",nativeQuery=true)
 Document getdocbyId(long id);
}
