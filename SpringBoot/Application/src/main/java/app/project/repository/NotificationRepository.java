package app.project.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import app.project.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{
	@Query(value="select * from notification where user_recu=:id",nativeQuery=true)
	List<Notification> GetAllNotif(long id);
	
	@Query(value="select * from notification where user_recu=:id and etat=0",nativeQuery=true)
	List<Notification> GetNotifNotSeen(long id);
	
	@Modifying
	@Transactional
	@Query(value="Delete from notification where user_recu=:id",nativeQuery=true)
	void DeleteAllNotif(long id);
	
	
	@Modifying
	@Transactional
	@Query(value="UPDATE notification SET etat=1 WHERE user_recu=:id",nativeQuery=true)
	void ChangerEtatNotif(long id);
}
