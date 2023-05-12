package app.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import app.project.entities.User;
import app.project.entities.specialite;


@EnableJpaRepositories
public interface SpecialiteRepository extends JpaRepository<specialite, Long>  {
	@Query(value="select * from specialite",nativeQuery=true)
	List<specialite> getAllSpec();
}
