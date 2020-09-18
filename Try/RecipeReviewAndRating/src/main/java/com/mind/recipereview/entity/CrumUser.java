//package com.mind.recipereview.entity;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.Transient;
//import javax.persistence.UniqueConstraint;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import com.mind.recipereview.validations.FieldMatch;
//import com.mind.recipereview.validations.ValidEmailServer;
//import com.mind.recipereview.validations.ValidPhoneServer;
//
//@FieldMatch.List({
//    @FieldMatch(first = "password", second = "confirmPassword", 
//    		    message = "The Password And ConfirmPassword Is Not Matching")
//})
//
//public class CrumUser {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer userId;
//	
//	@NotNull(message = "Is Required")
//	private String userName;
//	
//	@NotNull(message = "IsRequired")
//	@ValidEmailServer
//	private String email;
//	
//	@NotNull(message = "IsRequired")
//	@Pattern(regexp ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})"
//                       ,message = "Please Enter a Password In A Combination Of LowerCase And "
//    		           + "UpperCase And SpeciaSymbol")
//	private String password;
//	
//	@NotNull(message = "IsRequired")
//	@Pattern(regexp ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})"
//                        ,message = "Please Enter a Password In A Combination Of LowerCase And "
//     		            + "UpperCase And SpeciaSymbol")
//	private String confirmPassword;
//	
//	@NotNull(message = "IsRequired")
//	@ValidPhoneServer
//	private String phoneNumber;
//	
//	
//	public CrumUser() {
//		
//	}
//   
//	public CrumUser(Integer userId,  String userName, String email, String password, 
//		       String confirmPassword, String phoneNumber ) {
//		this.userId = userId;
//		this.userName = userName;
//		this.email = email;
//		this.password = password;
//		this.confirmPassword = confirmPassword;
//		this.phoneNumber = phoneNumber;
//	}
//    public Integer getUserId() {
//		return userId;
//	}
//    public void setUserId(Integer userId) {
//		this.userId = userId;
//	}
//    
//   public String getUserName() {
//	return userName;
//    }
//
//   public void setUserName(String userName) {
//	this.userName = userName;
//   }
//   public String getEmail() {
//		return email;
//	}
//    public void setEmail(String email) {
//		this.email = email;
//	}
//
//    public String getPassword() {
//		return password;
//	}
//
//    public void setPassword(String password) {
//		this.password = password;
//	}
//
//    public String getConfirmPassword() {
//		return confirmPassword;
//	}
//
//    public void setConfirmPassword(String confirmPassword) {
//		this.confirmPassword = confirmPassword;
//	}
//
//    public String getPhoneNumber() {
//		return phoneNumber;
//	}
//
//   public void setPhoneNumber(String phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//
//@Override
//public String toString() {
//	return "CrumUser [userId=" + userId + ", userName=" + userName + 
//			", email=" + email + ", password=" + password
//			+ ", confirmPassword=" + confirmPassword + ", phoneNumber=" 
//			+ phoneNumber + "]";
//}
//   
//   
//
//
//	
//    
//    
//}
