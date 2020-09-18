package com.mind.recipereview.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mind.recipereview.dao.LikeAndUnlikeDao;
import com.mind.recipereview.entity.LikeAndUnlike;

@Service
public class LikeAndUnlikeServiceImple implements LikeAndUnlikeService {
	
	@Autowired
	LikeAndUnlikeDao likeAndUnlikeDao;

	@Override
	public LikeAndUnlike save(LikeAndUnlike likeAndUnlike) {
		
		LikeAndUnlike theLikeAndUnlik=likeAndUnlikeDao.save(likeAndUnlike);
		return theLikeAndUnlik;
	}

	@Override
	public Page<LikeAndUnlike> findAll(Pageable pageable) {
		
		Page<LikeAndUnlike> likesAndUnlikes=likeAndUnlikeDao.findAll(pageable);
		return likesAndUnlikes;
	}

	@Override
	public LikeAndUnlike findById(Integer id) {
		
		Optional<LikeAndUnlike> theLikeAndUnlike=likeAndUnlikeDao.findById(id);
		LikeAndUnlike likeAndUnlike=null;
		if(theLikeAndUnlike.isPresent()) {
			likeAndUnlike=theLikeAndUnlike.get();
		}
		else {
			throw new RuntimeException("Did not find  id - " + id);
		}
		return likeAndUnlike;
		
	}

	@Override
	public LikeAndUnlike getSameRecipe(Integer recipeId) {
		LikeAndUnlike theLikeAndUnlike=likeAndUnlikeDao.getSameRecipe(recipeId);
		if(theLikeAndUnlike==null) {
			throw new RuntimeException("Did Not Find recipeId I Service"+recipeId);
		}
		return theLikeAndUnlike;
	}

}
