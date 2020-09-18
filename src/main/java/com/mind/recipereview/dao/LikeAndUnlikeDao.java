package com.mind.recipereview.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.mind.recipereview.entity.LikeAndUnlike;

@Repository
public interface LikeAndUnlikeDao extends JpaRepository<LikeAndUnlike, Integer> {
	
	@Query("SELECT dd FROM LikeAndUnlike dd WHERE dd.recipeId = ?1")
	public LikeAndUnlike getSameRecipe(Integer recipeId);

}
