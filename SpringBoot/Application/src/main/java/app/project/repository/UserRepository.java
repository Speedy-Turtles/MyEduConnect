
package app.project.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.project.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
  
	@Query(value="select * from user where email=:email",nativeQuery=true)
	User getUserByemail(String email);
	
	@Query(value="select * from user where id=:id",nativeQuery=true)
	User getUserById(long id);
	
	@Query(value="select * from user where id!=:id",nativeQuery=true)
	 List<User> getUserChat(long id);
	
	@Query(value="select * from user where cin=:cin",nativeQuery=true)
	User getUserByCin(String cin);
	
    @Query(value="select * from user where idclasse=:id",nativeQuery=true)
    List<User> GetUserByClass(long id);
	
	@Query(value="select * from user where password_token=:token",nativeQuery=true)
	User CheckToken(String token);
	
	@Query(value="select status from user_role where user_id=:id limit 1",nativeQuery=true)
	int GetStatus(long id);
	
	@Query(value="select u.*,ur.status from user u,user_role ur where u.id=ur.user_id",nativeQuery=true)
	List<Object[]> getAllUsersWithStatus();
	
	@Query(value="select role_name from role r,user_role ur where r.id=ur.role_id and ur.user_id=:id ",nativeQuery=true)
	String GetRoleByIdUser(BigInteger id);
	
	@Query(value="select u.* from user u,role r,user_role ur where r.id=ur.role_id and ur.user_id=u.id and r.role_name='chefDepartment';",nativeQuery=true)
	User GetChefDepartment();	
	
	@Query(value="select count(*)  from user where sex ='Man'  ",nativeQuery=true)
	int  CountUserMan();	
	
	@Query(value="select count(*)  from user where sex ='Woman'  ",nativeQuery=true)
	int  CountUserWomen();
	
	@Query(value="select count(*)  from user u,role r,user_role ur where u.id = ur.user_id  and ur.role_id=r.id and role_name='ensignant' ",nativeQuery=true)
	int  CountUserEnseignants();
	
	@Query(value="select count(*)  from user u,role r,user_role ur where u.id = ur.user_id  and ur.role_id=r.id and role_name='etudiant' ",nativeQuery=true)
	int  CountUserEtudiant();

	
}

