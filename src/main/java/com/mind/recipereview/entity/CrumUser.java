package com.mind.recipereview.entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.mind.recipereview.validations.FieldMatch;
import com.mind.recipereview.validations.ValidEmailServer;
import com.mind.recipereview.validations.ValidPhoneServer;


@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "The Password And ConfirmPassword Is Not Matching")
})

public class CrumUser 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@NotNull(message="is Required")
	@Column(name="username")
	private String userName;
	
	@NotNull(message="is Reqiured")
	@Column(name="email")
	@ValidEmailServer
	private String email;
	
	
	@NotNull(message="is Reqiured")
	@Size(min = 8,max = 15)
	@Pattern(regexp ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})",message = "Please Enter a Password In A Combination Of LowerCase And UpperCase And SpeciaSymbol")
	@Column(name="password")
	private String password;
	
	@NotNull(message="isRequired")
	@Size(min = 8,max = 15)
	@Pattern(regexp ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})",message = "Please Enter a Password In A Combination Of LowerCase And UpperCase And SpeciaSymbol")
	@Transient
	private String confirmPassword;
	
	@NotNull(message="is Reqiured")
	@Column(name="phonenumber")
	@ValidPhoneServer
	private String phoneNumber;
	
	public CrumUser() {
		super();
	}



	public CrumUser(Integer id, @NotNull(message = "is Required") String userName,
			@NotNull(message = "is Reqiured") @Size(min = 8, max = 15, message = "Please Enter a Password Between 8 To 15 Charcters And This Should Conatin Lower And Upper And Special Charcter") @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})", message = "Please Enter a Password In A Combination Of LowerCase And UpperCase And SpeciaSymbol") String password,
			@NotNull(message = "isRequired") @Size(min = 8, max = 15, message = "Please Enter a Password Between 8 To 15 Charcters And This Should Conatin Lower And Upper And Special Charcter") @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})", message = "Please Enter a Password In A Combination Of LowerCase And UpperCase And SpeciaSymbol") String confirmPassword,
		     @NotNull(message = "is Reqiured") String phoneNumber,
			@NotNull(message = "is Reqiured") String email) {
		
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
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



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	
	
	

	
	

	
	
	

}
