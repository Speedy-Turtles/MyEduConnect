package app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.project.entities.Role;
import app.project.entities.classe;

public interface ClasseRepository  extends JpaRepository<classe, Long> {
	@Query(value="select * from classe where idspec=:id",nativeQuery=true)
	List<classe> getClasseByIdSpec(long id);
	
	@Query(value="select * from classe where idclasse=:id",nativeQuery=true)
	classe getClasseById(long id);
}
