package com.mind.recipereview.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mind.recipereview.entity.Rating;
import com.mind.recipereview.entity.RecipeDetail;
import com.mind.recipereview.entity.User;
import com.mind.recipereview.exceptionhandling.ErrorEntity;
import com.mind.recipereview.service.IUserService;
import com.mind.recipereview.service.RatingService;
import com.mind.recipereview.service.RecipeService;
@Controller
@RequestMapping("/rating")
public class RatingController {
	
	@Autowired
	RatingService ratingService;
	
	@Autowired
	RecipeService recipeService;
	
	@Autowired
	IUserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	
	
	@PostMapping(value = "/saverating")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String saveRating(@Valid @RequestParam("recipeId") Integer recipeId, @ModelAttribute("rating") Rating rating,  
			                         BindingResult theBindingResult,Model theModel,Authentication authentication) {
		
		
		
		
		//  Through This We Can find recipeId And Through recipeId We Can Find picture related To That Recipe
		RecipeDetail recipeDetail=recipeService.findById(recipeId);
		
		User user=userService.findByUserName(authentication.getName());

		
	  
		
		Rating theRating=new Rating();
		theRating.setRecipeId(recipeId);
		theRating.setRating(rating.getRating());
		theRating.setComment(rating.getComment());
		theRating.setUserId(user.getUserId());
		// saving the details into the database
		Rating saverating=ratingService.save(theRating);
		
		System.out.println("Rating Data is Saved");
		
		
		return "redirect:/recipe/findall";
	}
	
	@GetMapping(value = "/myrecipe")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String myRecipe(Authentication authentication,Model theModel) {
		
		User user= userService.findByUserName(authentication.getName());
		
		System.out.println("Welcome"+authentication.getName());
		
		//return ResponseEntity.ok(recipeService.getRecipeByUserId(user.getUserId()));
		
		List<RecipeDetail> userRecipes=recipeService.findall();
		
		theModel.addAttribute("userRecipes", userRecipes);
		
		return "myhome";
		
	}
	
	

}
