package app.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.project.entities.Forum;

public interface ForumRepository extends JpaRepository<Forum, Long> {

}
