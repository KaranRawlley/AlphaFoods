package com.mind.recipereview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mind.recipereview.entity.LikeAndUnlike;

public interface LikeAndUnlikeService {
	
	public LikeAndUnlike save(LikeAndUnlike likeAndUnlike);
	public Page<LikeAndUnlike> findAll(Pageable pageable);
	public LikeAndUnlike findById(Integer id);
	public LikeAndUnlike getSameRecipe(Integer recipeId);
	
	


}
