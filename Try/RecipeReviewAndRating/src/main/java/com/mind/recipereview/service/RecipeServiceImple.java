package com.mind.recipereview.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mind.recipereview.dao.RecipeDao;
import com.mind.recipereview.entity.RecipeDetail;

@Service
public class RecipeServiceImple implements RecipeService {
	
	@Autowired
	RecipeDao recipeDao;

	@Override
	public RecipeDetail saveRecipeDetail(RecipeDetail recipeDetail) {
		
		RecipeDetail theRecipeDetail=recipeDao.save(recipeDetail);
		return theRecipeDetail;
	}

	@Override
	public List<RecipeDetail> findall() {
		
		List<RecipeDetail> recipes=recipeDao.findAll();
		return recipes;
	}

	@Override
	public RecipeDetail findById(Integer id) {
		
		Optional<RecipeDetail> theRecipe=recipeDao.findById(id);
		RecipeDetail recipeDetail=null;
		if(theRecipe.isPresent())
			recipeDetail=theRecipe.get();
		else {
			
			// we didn't Recipe the user
			throw new RuntimeException("Did not find Recipe id - " + id);
		}
		return recipeDetail;
	}

	@Override
	public void deleteById(Integer id) {
		
		Optional<RecipeDetail> theRecipe=recipeDao.findById(id);
		if(theRecipe.isPresent())
			recipeDao.deleteById(id);
		else {
			
			// we didn't Recipe the user
			throw new RuntimeException("Did not find Recipe id - " + id);
		}
		
		
	}

	@Override
	public Page<RecipeDetail> findAllRecipesUsingPage(Pageable pageable) {
		
		Page<RecipeDetail> pages=recipeDao.findAll(pageable);
		return pages;
		
	}

}
