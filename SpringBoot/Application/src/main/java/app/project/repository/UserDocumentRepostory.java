package app.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.project.entities.UserDocument;

public interface UserDocumentRepostory extends JpaRepository<UserDocument, Long> {

}
