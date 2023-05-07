package app.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.project.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
 
}
