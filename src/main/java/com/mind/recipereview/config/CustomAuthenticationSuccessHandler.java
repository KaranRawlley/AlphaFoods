package com.mind.recipereview.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.mind.recipereview.entity.User;
import com.mind.recipereview.service.IUserService;



@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

   
    
    @Autowired
    IUserService userservice;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n");
		System.out.println("==================================================>");

		String userName = authentication.getName();
	
		
		System.out.println("userName=" + userName);

		//User theUser = userService.findByUsername(userName);
		
		User theUser=userservice.findByUserName(userName);
		
		// now place in the session
		HttpSession session = request.getSession();
		session.setAttribute("user", theUser);
		
		// forward to home page
		
		response.sendRedirect(request.getContextPath() + "/");
	}

}
