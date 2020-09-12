package com.mind.recipereview.controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mind.recipereview.entity.ERole;
import com.mind.recipereview.entity.Role;
import com.mind.recipereview.entity.User;
import com.mind.recipereview.jwt.requestresonce.SignUpRequest;
import com.mind.recipereview.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	BCryptPasswordEncoder bcrypt;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@PostMapping(value="/signup")
	public ResponseEntity<String> registration(@Valid @RequestBody SignUpRequest signUpRequest) {
		
		String userEmail=signUpRequest.getEmail();
		logger.info("Processing Registration Form For "+userEmail);
	    
		// checking for if Some user already Register Within Same Email
	    User existingUserByEmail=userService.findByEmail(userEmail);
	    if(existingUserByEmail!=null) {
	    	throw new RuntimeException("User Email is Already Exist");
	    }
	    // checking for if Some user already Register Within Same userName
	    User existingUserByUsername=userService.findByUserName(signUpRequest.getUserName());
	    if(existingUserByUsername!=null) {
	    	throw new RuntimeException("UserName is Already Exist");
	    }
	    
	    else {
	    	  // else Create a New User in The DataBase
	    	
	    	User user=new User();
	    	
	    	user.setUserName(signUpRequest.getUserName());
	    	user.setEmail(signUpRequest.getEmail());
	    	
	    	// set the encrypt password
	        String  encodedPassword = bcrypt.encode(signUpRequest.getPassword());
	    	user.setPassword(encodedPassword);
	    	user.setPhoneNumber(signUpRequest.getPhoneNumber());
	    	
	    	// set The Current Date and Time
		    Date obj = new Date();
		    user.setUserregisterdate(obj);
		    
		    // Set The Default Role In DataBase
		    
		    List<Role>roles=new ArrayList<>();
		    roles.add(new Role(ERole.ROLE_USER));
		    
		    // set The Admin Role For One User
		   // roles.add(new Role(ERole.ROLE_ADMIN));
		    
		    user.setRoles(roles);
		    
		    // saving all the data in the database
    		User theUser=userService.save(user);
    		System.out.println(theUser);
    		logger.info("User Regiseter Sucessfully");
    		
    		return new ResponseEntity<>("user is created Sucessfully",HttpStatus.OK);
		 }
		
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
	@DeleteMapping(value = "/deleteuser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userid") Integer userId) {
		userService.deleteById(userId);
		return new ResponseEntity<>("Student is Deleted Sucessfully",HttpStatus.OK);
	}
	@GetMapping(value = "/test")
	public ResponseEntity<String> test(){
		return new ResponseEntity<>("Welcome To PostMan",HttpStatus.OK);
	}

}
