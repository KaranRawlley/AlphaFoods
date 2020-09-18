package com.mind.recipereview.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mind.recipereview.controlleradvice.RecieExceptionHandler;
import com.mind.recipereview.dao.RecipeDao;
import com.mind.recipereview.entity.Rating;
import com.mind.recipereview.entity.RecipeDetail;
import com.mind.recipereview.entity.User;
import com.mind.recipereview.service.IUserService;
import com.mind.recipereview.service.RatingService;
import com.mind.recipereview.service.RecipeService;
@Controller
@RequestMapping("/recipe")
public class RecipeHandleController {
	
	@Autowired
	RecipeService recipeService;
	
	@Autowired
	RecipeDao recipeDao;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	RatingService ratingService;
	
	private Integer commentRecipeId;
	
	@GetMapping(value="/findall")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String findAll(Model theModel,Authentication authentication){
		
		List<RecipeDetail> recipes=recipeService.findall();
		theModel.addAttribute("recipPages", recipes);
		System.out.println("BEFOR RETUring Data");
		return "myhome";
	}
	
	
	
	@GetMapping(value="/samerecipe")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String samedsih(@RequestParam("id") Integer id,Model theModel) {
		
		this.commentRecipeId=id;
		RecipeDetail recipeDetail=recipeService.findById(id);
		theModel.addAttribute("recipe", recipeDetail);
		Rating rating =new Rating();
		theModel.addAttribute("rating", rating);
		return "myread" ;
	}
	@GetMapping(value="/addrecipe")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String addRecie(Model theModel) {
		Rating rating =new Rating();
		theModel.addAttribute("rating", rating);
		return "myaddrecipe";
	}
	@GetMapping(value="/userrecipes")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String userrecipes() {
		return "UserRecipe";
	}
	
	@GetMapping(value = "/myrecipe")
	@PreAuthorize("hasRole('ROLE_USER')")
	public  String myRecipe(Authentication authentication,Model theModel) {
		
		User user= userService.findByUserName(authentication.getName());
		
		System.out.println("Welcome"+authentication.getName());
		
		List<RecipeDetail> uesrRecipes=recipeService.userRecipes(user.getUserId());
		
		theModel.addAttribute("uesrRecipes", uesrRecipes);
		
		return "UserRecipe";
	}
	@PostMapping(value = "/update")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String updateRecipe(@RequestParam("id") Integer id,Model theModel) {
		
		RecipeDetail recipeDetail=recipeService.findById(id);
		theModel.addAttribute("recipeDetail", recipeDetail);
		return "UpdateRecipe";
	}
	
	@PostMapping(value="/saveupdate")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String saveupdate(@RequestParam("id") Integer id,@ModelAttribute("recipeDetail") RecipeDetail recipeDetail,Authentication authentication) {
		
		User user=userService.findByUserName(authentication.getName());
		
		RecipeDetail theRecipeDetail=new RecipeDetail();
		
		theRecipeDetail.setUserId(user.getUserId());
		System.out.println("--------------------------->"+user.getUserId());
		theRecipeDetail.setId(id);
		theRecipeDetail.setCookingTime(recipeDetail.getCookingTime());
		System.out.println("---------------------------------------->"+recipeDetail.getCookingTime());
		theRecipeDetail.setRecipeName(recipeDetail.getRecipeName());
		theRecipeDetail.setDescription(recipeDetail.getDescription());
		recipeService.saveRecipeDetail(theRecipeDetail);
		 return "redirect:/recipe/myrecipe";
	}
	
	 @PostMapping("/delete")
	 @PreAuthorize("hasRole('ROLE_USER')")
	 public String deleteRecipe(@RequestParam("id") Integer id) {
			
			// delete the employee
	    recipeService.deleteById(id);
	    return "redirect:/recipe/myrecipe";
			
		}
	 @GetMapping(value="/getallcomment")
	 public List<Rating> getallcomment(Model theModel) {
		 
		 List<Rating> recipeComments= ratingService.getRecipeByUserId(this.commentRecipeId);
		 theModel.addAttribute("recipesComments", recipeComments);
		 return recipeComments;
	}
	 
	

	
	
	

	   
	
	
}
