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
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mind.recipereview.entity.ERole;
import com.mind.recipereview.entity.Role;
import com.mind.recipereview.entity.User;
import com.mind.recipereview.service.IUserService;
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	BCryptPasswordEncoder bcrypt;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	
	
	@GetMapping(value="/allusers")
	public String allUsers(Model theModel){
		
		List<User>allUsers=userService.findAll();
		theModel.addAttribute("allUsers", allUsers);
		return "adminhome";
	}
	
	@GetMapping(value = "/findOne")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User findOneUser(@RequestParam("userId") Integer userId) {
		
		User theUser =userService.findById(userId);
		return theUser;
	}
	
	@DeleteMapping(value = "/delete")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteOneUser(@RequestParam("userId") Integer userId) {
		
		userService.deleteById(userId);
		return new ResponseEntity<>("User is Deleted Sucessfully",HttpStatus.OK);
	}
	@PutMapping(value = "/update")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> updateOneUser(@RequestBody User user){
		
		userService.save(user);
		return new ResponseEntity<>("User is Updated Sucessfully",HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/test")
	@PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> test(){
		return new ResponseEntity<>("Welcome To PostMan",HttpStatus.OK);
	}
	
	

}
