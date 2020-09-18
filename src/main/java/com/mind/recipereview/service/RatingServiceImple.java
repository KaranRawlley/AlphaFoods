package com.mind.recipereview.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mind.recipereview.dao.RatingDao;
import com.mind.recipereview.entity.Rating;
@Service
public class RatingServiceImple implements RatingService{

	@Autowired
	RatingDao ratingDao;
	
	@Override
	public Rating save(Rating rating) {
		Rating theRating=ratingDao.save(rating);
		return theRating;
	}

	@Override
	public List<Rating> findall() {
		List<Rating> theRatings=ratingDao.findAll();
		return theRatings;
	}

	@Override
	public Rating findById(Integer id) {
		
		Optional<Rating>theRating=ratingDao.findById(id);
		Rating rating=null;
		if(theRating.isPresent()) {
			rating=theRating.get();
		}
		else {
			new RuntimeException("Can Not Find Rating id"+id);
		}
		return rating;
	}

	@Override
	public Page<Rating> findAllRatingPage(Pageable pageable) {
		
		Page<Rating> theRatings=ratingDao.findAll(pageable);
		return theRatings;
	}

	@Override
	public List<Rating> getRecipeByUserId(Integer recipeId) {
		
		List<Rating> recipeComments=ratingDao.getRecipeByUserId(recipeId);
		
		return recipeComments;
	}

}
