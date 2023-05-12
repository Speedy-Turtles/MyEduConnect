package app.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.project.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
