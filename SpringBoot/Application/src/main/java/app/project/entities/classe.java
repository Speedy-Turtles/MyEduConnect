package app.project.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity@Data@AllArgsConstructor@NoArgsConstructor
public class classe  {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idclasse;
  
  private String nom;
  
  @ManyToOne
  @JoinColumn(name="idspec")
  private specialite spec;
   
  @CreationTimestamp
  private Timestamp created_at;
	
  @UpdateTimestamp
  private Timestamp updated_at;
  
  /*@OneToMany(mappedBy = "classe")
  @JsonIgnore
  private List<User> users;*/
  
}
