package com.mind.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mind.entity.UserRolesTable;
import com.mind.entity.Role;
import com.mind.entity.User;
import com.mind.service.IUserService;
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@PostMapping(value="/registration")
	public String registration(@Valid @ModelAttribute("User") User user, 
			                 BindingResult theBindingResult, Model theModel) {
		
		String userEmail=user.getEmail();
		logger.info("Processing Registration Form For "+userEmail);
		
		// form validation
	    if (theBindingResult.hasErrors())
	    	return "registration-form";
	    
	    User existingUser=userService.findByEmail(userEmail);
	    
	    if(existingUser!=null) {
	    	theModel.addAttribute("registrationError", "This Email Is Already "
	    			               + "Exist in Our Database");
	    	return "registration-form";
	    }
	    else {
	    	if(user!=null) {
	    		List<Role> userRoles=new ArrayList<>();
	    		userRoles.add(new Role(UserRolesTable.ROLE_USER));
	    		// For Setting By Default Admin To One User
	    		userRoles.add(new Role(UserRolesTable.ROLE_ADMIN));
	    		// saving The User Data
	    		user.setRoles(userRoles);
	    		User theUser=userService.save(user);
	    		System.out.println(theUser);
	    		logger.info("User Regiseter Sucessfully");
	    		return "registration-confirmation";
	    	}
	    	
	    	
	    }
		return "Test";
        
					 
				 
		
		
		
	}
	
	@GetMapping(value="/allusers")
	public List<User>allUsers(){
		
		List<User>allusers=userService.findAll();
		return allusers;
	}
	
	@GetMapping(value = "/findOne/{userId}")
	public User findOneUser(@PathVariable("userId") Integer userId) {
		
		User theUser =userService.findById(userId);
		return theUser;
	}
	@DeleteMapping(value = "/delete_user/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userid") Integer userId) {
		userService.deleteById(userId);
		return new ResponseEntity<>("Student is Deleted Sucessfully",HttpStatus.OK);
	}

}
