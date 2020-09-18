package com.mind.recipereview.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "ratingdetail")
public class Rating {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="recipeId")
	private Integer recipeId;
	
	@Column(name="userId")
	private Integer userId;
	
	
	@Column(name="rating")
	private Integer rating;
	
	
	@Column(name="comment")
	private String comment;

	public Rating() {
	}

	public Rating(Integer id, Integer recipeId, Integer userId,
			@Min(value = 1, message = "please enter a comment between 1 to 10") 
	        @Max(value = 10, message = "Please Enter a Comment Betwwen 1 to 10")
	         @NotNull(message = "Is Required") Integer rating,
			@NotNull(message = "isRequired") String comment) {
		
		this.id = id;
		this.recipeId = recipeId;
		this.userId = userId;
		this.rating = rating;
		this.comment = comment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Rating [id=" + id + ", recipeId=" + recipeId + ", userId=" + userId + ", rating=" + rating
				+ ", comment=" + comment + "]";
	}
	
	
	
	
	
	

}
