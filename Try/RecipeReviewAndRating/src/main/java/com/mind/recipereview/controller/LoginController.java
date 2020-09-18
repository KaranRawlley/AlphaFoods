package com.mind.recipereview.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mind.recipereview.config.MyUserDetail;
import com.mind.recipereview.entity.ERole;
import com.mind.recipereview.entity.Role;
import com.mind.recipereview.entity.User;
import com.mind.recipereview.jwt.requestresonce.JwtResponce;
import com.mind.recipereview.jwt.requestresonce.LoginRequest;
import com.mind.recipereview.jwt.requestresonce.SignUpRequest;
import com.mind.recipereview.jwtt.JwtUtil;
import com.mind.recipereview.service.IUserService;
@RestController
@RequestMapping("/CookingRecipe")
public class LoginController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	IUserService userService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtil jwtUtils;
	
	@Autowired
	BCryptPasswordEncoder bcrypt;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), 
						loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		MyUserDetail userDetails = (MyUserDetail) authentication.getPrincipal();	
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		

       // Dougth Here
//       return ResponseEntity.ok(new JwtResponce(jwt,"Bearer",userDetails.getUsername(),
//    		                    roles ));
		
		return ResponseEntity.ok(new JwtResponce(jwt));
	}
	
	@PostMapping(value="/signup")
	public ResponseEntity<String> registration(@Valid @RequestBody SignUpRequest 
			                                         signUpRequest) {
		
		String userEmail=signUpRequest.getEmail();
		logger.info("Processing Registration Form For "+userEmail);
	    
		// checking for if Some user already Register Within Same Email
	    User existingUserByEmail=userService.findByEmail(userEmail);
	    if(existingUserByEmail!=null) {
	    	throw new RuntimeException("User Email is Already Exist");
	    }
	    // checking for if Some user already Register Within Same userName
	    User existingUserByUsername=userService.findByUserName(signUpRequest.
	    		                                                getUserName());
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
    
	@GetMapping(value = "/afterlogin")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String afterLogin() {
		return "Test";
	}

}
