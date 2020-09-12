package com.mind.recipereview.entity;
import java.util.ArrayList;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mind.recipereview.validations.FieldMatch;
import com.mind.recipereview.validations.ValidEmailServer;
import com.mind.recipereview.validations.ValidPhoneServer;


@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", 
    		    message = "The Password And ConfirmPassword Is Not Matching")
})

@Entity
@Table(name="users_record",
       uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
      }
)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid")
	private Integer userId;
	
	@NotNull(message = "Is Required")
	@Column(name="username")
	private String userName;
	
	@NotNull(message = "IsRequired")
	@ValidEmailServer
	@Column(name="email")
	private String email;
	
	@NotNull(message = "IsRequired")
	@Column(name="password")
	private String password;
	
	
	@NotNull(message = "IsRequired")
	@ValidPhoneServer
	@Column(name="phonenumber")
	private String phoneNumber;
	
	@Column(name="createdatetime")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
	private Date userregisterdate;
	
	@ManyToMany( cascade = { CascadeType.ALL },fetch = FetchType.EAGER)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "users_record_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();
	
	
	public User() {
		
	}
   
	public User(Integer userId,  String userName, String email, String password, 
		        String phoneNumber, Date userregisterdate, 
		       List<Role> roles) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.userregisterdate = userregisterdate;
		this.roles = roles;
	}
    public Integer getUserId() {
		return userId;
	}
    public void setUserId(Integer userId) {
		this.userId = userId;
	}
    
   public String getUserName() {
	return userName;
    }

   public void setUserName(String userName) {
	this.userName = userName;
   }
   public String getEmail() {
		return email;
	}
    public void setEmail(String email) {
		this.email = email;
	}

    public String getPassword() {
		return password;
	}

    public void setPassword(String password) {
		this.password = password;
	}

   

    public String getPhoneNumber() {
		return phoneNumber;
	}

   public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
   
   public Date getUserregisterdate() {
	   return userregisterdate;
    }
   
   public void setUserregisterdate(Date userregisterdate) {
	   this.userregisterdate = userregisterdate;
   }

	public List<Role> getRoles() {
		return roles;
	}

    public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName +
				", email=" + email + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + 
				", userregisterdate=" + userregisterdate + ", roles=" + roles + "]";
	}
    
    
    
    
}
