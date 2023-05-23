package app.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class EmbedIdDocument implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "document_id")
	 Long documentId;
	
	@Column(name = "user_id")
	 Long userId;
}
