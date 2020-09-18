package com.mind.recipereview.service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.mind.recipereview.entity.Rating;
import com.mind.recipereview.entity.RecipeDetail;

public interface RatingService {
	
	public Rating save(Rating rating);
	public List<Rating> findall();
	public Rating findById(Integer id);
	public Page<Rating> findAllRatingPage(Pageable pageable);
	public List<Rating>getRecipeByUserId(Integer recipeId);
 
}
