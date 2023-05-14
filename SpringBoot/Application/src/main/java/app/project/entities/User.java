
package app.project.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnDefault;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String FirstName;
	private String LastName;
	private String email; 
	private String num_tlf;
	private String password;
	@ColumnDefault(value = "null")
	@Column(length = 999999999) 
	private String Photo;
	private String Cin;
	private String sex;
	private LocalDate Birth_day;
	@ColumnDefault(value = "false")
	private Boolean welcome_field;
	@ColumnDefault(value = "null")
	private String  password_token;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@ColumnDefault(value = "null")
	private Date password_token_send_ats;
	
	@CreationTimestamp
	private Timestamp created_at;
	
	@UpdateTimestamp
	private Timestamp updated_at;
	
	@Temporal(TemporalType.TIMESTAMP)
	@ColumnDefault(value = "null")
	private Date email_verified_at;
	
	
	@ManyToOne
	@JoinColumn(name="idclasse")
	private classe classe;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name="user_role",
	joinColumns = @JoinColumn(name="user_id"),
	inverseJoinColumns = @JoinColumn(name="role_id"))
	private List<Role> role;

}

