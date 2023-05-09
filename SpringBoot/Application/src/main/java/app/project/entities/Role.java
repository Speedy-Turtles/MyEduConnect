<<<<<<< HEAD
package app.project.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor@NoArgsConstructor@Data

public class Role implements Serializable {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private long id;
 
 private String RoleName;
 
    @CreationTimestamp
	private Timestamp created_at;
	
	@UpdateTimestamp
	private Timestamp updated_at;
 
}
=======
package app.project.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor@NoArgsConstructor@Data

public class Role implements Serializable {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private long id;
 
 private String RoleName;
 
    @CreationTimestamp
	private Timestamp created_at;
	
	@UpdateTimestamp
	private Timestamp updated_at;
 
}
>>>>>>> c475a485596fd9a763dff634b70b71401dae7c73
