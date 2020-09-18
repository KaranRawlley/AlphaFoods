package com.mind.recipereview.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mind.recipereview.entity.RecipeDetail;

public interface RecipeService {
	
	public RecipeDetail saveRecipeDetail(RecipeDetail recipeDetail);
	public List<RecipeDetail> findall();
	public RecipeDetail findById(Integer id);
	public void deleteById(Integer id);
	public Page<RecipeDetail>findAllRecipesUsingPage(Pageable pageable);
	
	

}
