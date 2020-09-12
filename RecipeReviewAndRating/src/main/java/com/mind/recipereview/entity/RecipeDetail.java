package com.mind.recipereview.entity;
import java.util.Arrays;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="recipedetails")
public class RecipeDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="userId")
	private Integer userId;
	
	@Column(name = "recipename")
	private String recipeName;
	
	@Column(name="cookingtime")
	private String cookingTime;
	
	@Column(name="description")
	private String description;
	
	@Column(name="filepath")
	private String filepath;
	
	@Column(name="createdate")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
	private Date recipeRegisterDate;
	
	@Column(name = "filename")
	private String fileName;
	
	@Column(name = "filetype")
	private String fileType;
	
	

	public RecipeDetail() {
		super();
	}
    public RecipeDetail(Integer id,Integer userId, String recipeName, String cookingTime, 
    		            String description, String filepath,
			            Date recipeRegisterDate, String fileName, String fileType) {
		this.id = id;
		this.userId=userId;
		this.recipeName = recipeName;
		this.cookingTime = cookingTime;
		this.description = description;
		this.filepath = filepath;
		this.recipeRegisterDate = recipeRegisterDate;
		this.fileName = fileName;
		this.fileType = fileType;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	public String getCookingTime() {
		return cookingTime;
	}
	public void setCookingTime(String cookingTime) {
		this.cookingTime = cookingTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public Date getRecipeRegisterDate() {
		return recipeRegisterDate;
	}
	public void setRecipeRegisterDate(Date recipeRegisterDate) {
		this.recipeRegisterDate = recipeRegisterDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "RecipeDetail [id=" + id + ", userId=" + userId + ","
				+ " recipeName=" + recipeName + ", cookingTime="
				+ cookingTime + ", description=" + description + ", "
						+ "filepath=" + filepath + ", recipeRegisterDate="
				+ recipeRegisterDate + ", fileName=" + fileName + 
				", fileType=" + fileType + "]";
	}
	

	
	
}
