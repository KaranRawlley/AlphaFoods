package com.mind.recipereview.controller;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mind.recipereview.entity.RecipeDetail;
import com.mind.recipereview.service.RecipeService;

@RestController
@RequestMapping("/recipe")
public class RecipeHandleController {
	
	@Autowired
	RecipeService recipeService;
	
	@GetMapping(value = "/recipelist")
	public Page<RecipeDetail> findAllRecipe(@RequestParam(name = "page") Optional<Integer> reqPage,
			                               @RequestParam(name = "size") Optional<Integer> reqSize) {
		Integer page = reqPage.orElse(1);
		Integer size = reqSize.orElse(12);
		page = page - 1;
		Pageable pageable = PageRequest.of(page, size, Direction.ASC, "id");
		Page<RecipeDetail> recipes=recipeService.findAllRecipesUsingPage(pageable);
		
		int totalPages = recipes.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed()
					                    .collect(Collectors.toList());
			
		}
		return recipes;
	}
	

}
