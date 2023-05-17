package authPrametre;

import java.math.BigInteger;
import java.sql.Timestamp;

import javax.persistence.Column;

import org.hibernate.annotations.CreationTimestamp;

import app.project.entities.RoleUser;
import app.project.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class MultipelDonne {
		private BigInteger id;
	    private String FirstName;
	    private String Lastname;
	    private String email;
	    @Column(length = 999999999) 
	    private String photo;
	    @CreationTimestamp
		private Timestamp created_at;
	    private String RoleName;
        private int status;
        
       
}
