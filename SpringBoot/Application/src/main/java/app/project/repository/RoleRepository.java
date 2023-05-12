
package app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.project.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	@Query(value="select * from role where id=:id",nativeQuery=true)
	Role getRoleById(long id);
	
	List<Role> findAll();
}

