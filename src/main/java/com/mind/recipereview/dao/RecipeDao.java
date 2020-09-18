package com.mind.recipereview.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mind.recipereview.entity.LikeAndUnlike;
import com.mind.recipereview.entity.RecipeDetail;

@Repository
public interface RecipeDao extends JpaRepository<RecipeDetail, Integer> {
	
	@Query("SELECT dd FROM RecipeDetail dd WHERE dd.userId = ?1")
	public List<RecipeDetail>getRecipeByUserId(Integer userId);
	
	@Query("SELECT dd FROM RecipeDetail dd WHERE dd.fileName = ?1")
	public RecipeDetail getSameRecipe(String recipeId);
	
	@Query("SELECT dd FROM RecipeDetail dd WHERE dd.userId = ?1")
	public List<RecipeDetail> getUserRecipes(Integer userId);
	
	

}
