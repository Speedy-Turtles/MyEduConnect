package app.project.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import app.project.entities.EmbedeId;
import app.project.entities.RoleUser;

public interface UserRoleRepository extends JpaRepository<RoleUser, EmbedeId> {
	@Query(value="select * from user_role where user_id=:id",nativeQuery=true)
	RoleUser getUserRole(long id);
	
	@Transactional
	@Modifying
	@Query(value="delete from user_role where user_id=:user_id and role_id=:role_id",nativeQuery=true)
	void deleteChefDep(long user_id, long role_id);
}
