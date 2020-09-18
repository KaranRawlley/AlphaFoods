package com.mind.recipereview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mind.recipereview.entity.LikeAndUnlike;
import com.mind.recipereview.service.LikeAndUnlikeService;
import com.mind.recipereview.service.RecipeService;

@RestController
@RequestMapping("/likeandunlike")
public class LikeAndUnlikeController {
	
	@Autowired
	RecipeService recipeService;
	
	@Autowired
	LikeAndUnlikeService likeAndUnlikeService;
	
	@GetMapping(value = "/home")
	public String home(Model theModel) {
		LikeAndUnlike likeAndUnlke=new LikeAndUnlike();
		theModel.addAttribute("student", likeAndUnlke);
		return "like";
	}
	@PostMapping(value = "/savelikeandunlike")
	public String saveAndUnlike(@RequestParam("id") Integer id ,
								@RequestParam("recipeId") Integer recipeId,
								@RequestParam("checkingLikeOrUnlike") String checkingLikeOrUnlike) {
		
		int like=0;
		int unlike=0;
		
		LikeAndUnlike likeAndUnlike=likeAndUnlikeService.getSameRecipe(recipeId);
		
		// Means This Recipe Recipe Have Likes And Dislikes
			if(checkingLikeOrUnlike.equals("like")) {
				like=likeAndUnlike.getLikeCount();
				like=like+1;
			}
			else {
				unlike=likeAndUnlike.getUnlikeCount();
				unlike=unlike-1;
			}
		
		LikeAndUnlike theLikeAndUnlike=new LikeAndUnlike();
		theLikeAndUnlike.setLikeCount(like);
		theLikeAndUnlike.setUnlikeCount(unlike);
		
		LikeAndUnlike test=likeAndUnlikeService.save(theLikeAndUnlike);
		System.out.println(test);
		
		return "Test";
	}

}
