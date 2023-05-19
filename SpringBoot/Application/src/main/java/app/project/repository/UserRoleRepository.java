package app.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.project.entities.EmbedeId;
import app.project.entities.RoleUser;
import app.project.entities.User;

public interface UserRoleRepository extends JpaRepository<RoleUser, EmbedeId> {
	@Query(value="select * from user_role where user_id=:id",nativeQuery=true)
	RoleUser getUserRole(long id);
	
	
}
