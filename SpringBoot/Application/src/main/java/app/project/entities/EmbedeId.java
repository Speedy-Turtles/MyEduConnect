<<<<<<< HEAD
package app.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmbedeId implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

  @Column(name="user_id")
  Long UserId;
  
  @Column(name="role_id")
  Long RoleId;
  
}
=======
package app.project.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmbedeId implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

  @Column(name="user_id")
  Long UserId;
  
  @Column(name="role_id")
  Long RoleId;
  
}
>>>>>>> c475a485596fd9a763dff634b70b71401dae7c73
