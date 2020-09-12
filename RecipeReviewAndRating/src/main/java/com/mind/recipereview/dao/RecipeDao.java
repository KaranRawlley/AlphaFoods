package com.mind.recipereview.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mind.recipereview.entity.RecipeDetail;

@Repository
public interface RecipeDao extends JpaRepository<RecipeDetail, Integer> {

}
