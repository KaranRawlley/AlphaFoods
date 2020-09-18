package com.mind.recipereview.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mind.recipereview.entity.Rating;


@Repository
public interface RatingDao  extends JpaRepository<Rating, Integer> {
	
	@Query("SELECT dd FROM Rating dd WHERE dd.recipeId = ?1")
	public List<Rating>getRecipeByUserId(Integer recipeId);

}
