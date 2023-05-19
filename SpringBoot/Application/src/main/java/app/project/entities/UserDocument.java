package app.project.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="user_document")
@Entity@Data@AllArgsConstructor@NoArgsConstructor
public class UserDocument implements Serializable {
	
	  @EmbeddedId
	  EmbedIdDocument id;
	  
	  @ManyToOne
	  @MapsId("UserId")
	  @JoinColumn(name="user_id")
	  User user;
	  
	 
	  @ManyToOne
	  @MapsId("DocumentId")
	  @JoinColumn(name="document_id")
	  Document document;
	  
	    @ColumnDefault(value = "0")
		private int etat;
	    
	    private int nombre;
	    
	    @ColumnDefault(value = "f")
	  	private String langue;
	  
	    @CreationTimestamp
		private Timestamp created_at;
		
		@UpdateTimestamp
		private Timestamp updated_at;
}
