package app.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.project.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{

}
