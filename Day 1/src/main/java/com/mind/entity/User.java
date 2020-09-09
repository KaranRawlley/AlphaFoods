package com.mind.entity;
import java.util.ArrayList;
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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.mind.validations.FieldMatch;
import com.mind.validations.ValidEmailServer;
import com.mind.validations.ValidPhoneServer;

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
	@Column(name="name")
	private String name;
	
	@NotNull(message = "IsRequired")
	@ValidEmailServer
	@Column(name="email")
	private String email;
	
	@NotNull(message = "IsRequired")
	@Pattern(regexp ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})"
                       ,message = "Please Enter a Password In A Combination Of LowerCase And "
    		           + "UpperCase And SpeciaSymbol")
	@Column(name="password")
	private String password;
	
	@NotNull(message = "IsRequired")
	@Pattern(regexp ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})"
                        ,message = "Please Enter a Password In A Combination Of LowerCase And "
     		            + "UpperCase And SpeciaSymbol")
	@Transient
	private String confirmPassword;
	
	@NotNull(message = "IsRequired")
	@ValidPhoneServer
	@Column(name="phonenumber")
	private String phoneNumber;
	
	@ManyToMany( cascade = { CascadeType.ALL },fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "users_record_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();
	
	
	
	public User() {
		
	}



	public User(Integer userId, @NotNull(message = "Is Required") String name,
			@NotNull(message = "IsRequired") String email, 
			@NotNull(message = "IsRequired") String password,
			@NotNull(message = "IsRequired") String confirmPassword,
			@NotNull(message = "IsRequired") String phoneNumber, List<Role> roles) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.phoneNumber = phoneNumber;
		this.roles = roles;
	}

   public Integer getUserId() {
		return userId;
	}

   public void setUserId(Integer userId) {
		this.userId = userId;
	}
    
   public String getName() {
		return name;
	}

   public void setName(String name) {
		this.name = name;
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

    public String getConfirmPassword() {
		return confirmPassword;
	}

    public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

    public String getPhoneNumber() {
		return phoneNumber;
	}

   public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

    public List<Role> getRoles() {
		return roles;
	}

    public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
    
    @Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ","
				+ " password=" + password + ", confirmPassword=" + 
				   confirmPassword + ", phoneNumber=" + phoneNumber + ", roles=" + roles + "]";
	}
    
    
	
	
	
	
	

}
