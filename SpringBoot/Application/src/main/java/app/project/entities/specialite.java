package app.project.entities;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity@Data@NoArgsConstructor@AllArgsConstructor
public class specialite {
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Long idspec;
	  
	  private String type;
	  private String niveau;
	  
	   @OneToMany(mappedBy = "spec", cascade = CascadeType.REMOVE)
	   @JsonIgnore
	   private List<classe> classes;
	   
	  @CreationTimestamp
	  private Timestamp created_at;
		
	  @UpdateTimestamp
	  private Timestamp updated_at;
	  
	 
}
