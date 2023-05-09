<<<<<<< HEAD
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
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="user_role")
@Entity@Data@NoArgsConstructor@AllArgsConstructor
public class RoleUser implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

  @EmbeddedId
  EmbedeId id;
  
  @ManyToOne
  @MapsId("UserId")
  @JoinColumn(name="user_id")
  User user;
  
 
  @ManyToOne
  @MapsId("RoleId")
  @JoinColumn(name="role_id")
  Role role;
  
    @ColumnDefault(value = "0")
	private int status;
  
    @CreationTimestamp
	private Timestamp created_at;
	
	@UpdateTimestamp
	private Timestamp updated_at;
}
=======
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
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="user_role")
@Entity@Data@NoArgsConstructor@AllArgsConstructor
public class RoleUser implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

  @EmbeddedId
  EmbedeId id;
  
  @ManyToOne
  @MapsId("UserId")
  @JoinColumn(name="user_id")
  User user;
  
 
  @ManyToOne
  @MapsId("RoleId")
  @JoinColumn(name="role_id")
  Role role;
  
    @ColumnDefault(value = "0")
	private int status;
  
    @CreationTimestamp
	private Timestamp created_at;
	
	@UpdateTimestamp
	private Timestamp updated_at;
}
>>>>>>> c475a485596fd9a763dff634b70b71401dae7c73
