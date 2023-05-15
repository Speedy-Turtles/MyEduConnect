package app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import app.project.entities.specialite;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@EnableJpaRepositories
public interface SpecialiteRepository extends JpaRepository<specialite, Long>  {
	@Query(value="select * from specialite",nativeQuery=true)
	List<specialite> getAllSpec();
	
	Page<specialite> findByType(String mc,Pageable pageable);
	
	/*@Query("select p from specialite p where p.designation like :x",nativeQuery=true)
	Page<specialite> Affiche(@Param("x") String mc,Pageable pageable);*/
}
