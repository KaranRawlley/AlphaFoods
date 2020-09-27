package com.mind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mind.entity.User;

@Controller
@RequestMapping("/CookingRecipe")
public class LoginController {
	
	@GetMapping(value="/registrationForm")
	public String show(Model theModel) {
		User user=new User();
		theModel.addAttribute("User",user);
		return "registration-form";
		
	}

}
