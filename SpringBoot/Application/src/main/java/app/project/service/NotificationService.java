package app.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.project.entities.Notification;
import app.project.entities.User;
import app.project.repository.NotificationRepository;
import app.project.repository.UserRepository;

@Service
public class NotificationService {
		@Autowired
		UserRepository UserRepo;
		
		@Autowired
		UserService userserv;
		
		@Autowired
		NotificationRepository notifrepo;
	
	  public List<Notification> getNotif(long id){
		  List<Notification> notifs=notifrepo.GetAllNotif(id);
		  return notifs;
	  }
	  
	  public List<Notification> getNotifNotSeenyet(long id){
		  List<Notification> notifs=notifrepo.GetNotifNotSeen(id);
		  return notifs;
	  }
	  
	  public void DeleteById(long id) {
		  notifrepo.deleteById(id);
	  }
	  
	  public void DeleteAllNotif(long id) {
		  notifrepo.DeleteAllNotif(id);
	  }
	  
	  public void updateAllNotifUser(long id) {
		  notifrepo.ChangerEtatNotif(id);
	  }
}
