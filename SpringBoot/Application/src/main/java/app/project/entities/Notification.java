package app.project.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity@Data@AllArgsConstructor@NoArgsConstructor
public class Notification implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;
  
  @ManyToOne
  @JoinColumn(name="user_envoi")
  User userEnvoi;
  
  @ManyToOne
  @JoinColumn(name="user_recu")
  User userRecu;
  
  private int etat;
  private String Message;
  

	@CreationTimestamp
	private Timestamp created_at;
	
	@UpdateTimestamp
	private Timestamp updated_at;
  
  
}
