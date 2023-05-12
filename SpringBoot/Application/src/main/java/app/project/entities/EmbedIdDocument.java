package app.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmbedIdDocument implements Serializable{
	  @Column(name="user_id")
	  Long UserId;
	  
	  @Column(name="document_id")
	  Long DocumentId;
}
