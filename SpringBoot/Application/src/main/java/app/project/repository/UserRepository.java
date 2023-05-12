
package app.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import app.project.entities.User;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
  
	@Query(value="select * from user where email=:email",nativeQuery=true)
	User getUserByemail(String email);
	
	@Query(value="select * from user where cin=:cin",nativeQuery=true)
	User getUserByCin(String cin);
	
	@Query(value="select * from user where password_token=:token",nativeQuery=true)
	User CheckToken(String token);
	
	@Query(value="select status from user_role where user_id=:id",nativeQuery=true)
	int GetStatus(long id);
	
  
}

