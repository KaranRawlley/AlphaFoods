package com.mind.recipereview.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mind.recipereview.entity.CrumUser;
import com.mind.recipereview.entity.ERole;
import com.mind.recipereview.entity.Role;
import com.mind.recipereview.entity.User;
import com.mind.recipereview.service.IUserService;
@Controller
@RequestMapping("/CookingRecipe")

public class LoginController {
	
	@Autowired
	IUserService userService;

	@Autowired
	BCryptPasswordEncoder bcrypt;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		return "mylogin";
	}
	@GetMapping("/showformforsignup")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		CrumUser theUser = new CrumUser();
		theModel.addAttribute("theUser", theUser);
		return "My";
	}
	
	@PostMapping(value="/registration")
	public String registration(@Valid @ModelAttribute("theUser") CrumUser crumuser, 
			                 BindingResult theBindingResult, Model theModel) {
		
		String userEmail=crumuser.getEmail();
		logger.info("Processing Registration Form For "+userEmail);
		
		// form validation
	    if (theBindingResult.hasErrors())
	    	return "My";
	 
	    // checking for if Some user already Register Within Same Email
	    User existingUserByEmail=userService.findByEmail(userEmail);
	    if(existingUserByEmail!=null) {
	    	theModel.addAttribute("registrationError", "This Email Is Already "
	    			               + "Exist in Our Database");
	    	return "My";
	    }
	    // checking for if Some user already Register Within Same userName
	    User existingUserByUsername=userService.findByUserName(crumuser.getUserName());
	    if(existingUserByUsername!=null) {
	    	theModel.addAttribute("registrationError", "This username Is Already "
	    			               + "Exist in Our Database");
	    	return "My";
	    }
	    
	    else {
	    	  // else Create a New User in The DataBase
	    	
	    	    User user=new User();
	    	    
	    	    user.setUserName(crumuser.getUserName());
	    	    user.setEmail(crumuser.getEmail());
	    	   
	    	    // Encoded Password Will Store In the Database
	    		 String  encodedPassword = bcrypt.encode(crumuser.getPassword());
	 	    	 user.setPassword(encodedPassword);
	 	    	 
	 	    	 user.setPhoneNumber(crumuser.getPhoneNumber());
	 	    	 
	 	    	// set The Current Time
	    		 Date obj = new Date();
	    		 // set the current date and time
	    		 user.setUserregisterdate(obj);
	    	 
	    		List<Role> userRoles=new ArrayList<>();
	    		 // setting the default role as user
	    		userRoles.add(new Role(ERole.ROLE_USER));
	    		// For Setting By Default Admin To One User
	    		userRoles.add(new Role(ERole.ROLE_ADMIN));
	    		
	    		// setting the role
	    		user.setRoles(userRoles);
	    		
	    		 
	    		
	    		
	 	    	// saving all the data in the database
	    		User theUser=userService.save(user);
	    		System.out.println(theUser);
	    		logger.info("User Regiseter Sucessfully");
	    		return "mylogin";
	      }
		
      }
	



}
