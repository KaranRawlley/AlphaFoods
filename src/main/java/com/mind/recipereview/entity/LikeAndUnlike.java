package com.mind.recipereview.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "likeandunlikerecord")
public class LikeAndUnlike {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="recipeId")
	private Integer recipeId;
	
	@Column(name="userId")
	private Integer userid;
	
	@Column(name="likecount")
	private int likeCount;
	
	@Column(name="unlikecount")
	private int unlikeCount;
	
	
	@Transient
    private String checkingLikeOrUnlike;

	public LikeAndUnlike() {
	}

	public LikeAndUnlike(Integer id, Integer recipeId, Integer userid, 
			             int likeCount, int unlikeCount,String checkingLikeOrUnlike) {
		this.id = id;
		this.recipeId = recipeId;
		this.userid = userid;
		this.likeCount = likeCount;
		this.unlikeCount = unlikeCount;
		this.checkingLikeOrUnlike=checkingLikeOrUnlike;
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

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getUnlikeCount() {
		return unlikeCount;
	}

	public void setUnlikeCount(int unlikeCount) {
		this.unlikeCount = unlikeCount;
	}

	

	public String getCheckingLikeOrUnlike() {
		return checkingLikeOrUnlike;
	}

	public void setCheckingLikeOrUnlike(String checkingLikeOrUnlike) {
		this.checkingLikeOrUnlike = checkingLikeOrUnlike;
	}
	
}
