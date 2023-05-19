package app.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.project.entities.Inbox;

public interface InboxRepository extends JpaRepository<Inbox, Long>{
 @Query(value="select * from inbox where user_envoi=:envoi and user_recu=:recu or user_envoi=:recu and user_recu=:envoi",nativeQuery=true)
 List<Inbox> getMessageprivate(long envoi,long recu);
}
