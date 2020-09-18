package com.mind.recipereview.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mind.recipereview.entity.RecipeDetail;
import com.mind.recipereview.entity.User;
import com.mind.recipereview.service.IUserService;
import com.mind.recipereview.service.RecipeService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	RecipeService recipeService;
	
	@GetMapping(value="/allusers")
	public String allUsers(Model theModel){
		
		List<User>allUsers=userService.findAll();
		theModel.addAttribute("allUsers", allUsers);
		return "adminhome";
	}
	@GetMapping(value="/findallrecipes")
	
	public String findAll(Model theModel,Authentication authentication){
		
		List<RecipeDetail> recipes=recipeService.findall();
		theModel.addAttribute("recipPages", recipes);
		
		return "adminhomerecipe";
	}

}
