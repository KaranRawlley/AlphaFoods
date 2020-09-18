package com.mind.recipereview.jwt.requestresonce;

import java.util.ArrayList;
import java.util.List;

import com.mind.recipereview.entity.Role;

public class JwtResponce 
{
	private String token;
	
	public JwtResponce() {
		super();
	}
	

	public JwtResponce(String token) {
		this.token=token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
	
/* 
    if we want to return the roles and username
	
	private String type = "Bearer";
	private String username;
	private List<String> roles= new ArrayList<>();


	
	public JwtResponce(String token, String type, String username, List<String> roles) {
	     super();
		 this.token = token;
		 this.type = type;
		 this.username = username;
		 this.roles = roles;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;	
	}
*/
	

