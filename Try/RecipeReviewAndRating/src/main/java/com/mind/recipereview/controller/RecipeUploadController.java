package com.mind.recipereview.controller;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.mind.recipereview.entity.RecipeDetail;
import com.mind.recipereview.entity.User;
import com.mind.recipereview.exceptionhandling.FileNotFoundException;
import com.mind.recipereview.exceptionhandling.FileStorageException;
import com.mind.recipereview.service.IUserService;
import com.mind.recipereview.service.RecipeService;
@RestController
@RequestMapping("/upload")
public class RecipeUploadController {
	
	@Autowired
	RecipeService recipeService;
	
	@Autowired
	IUserService userService;
	
	// BREAKING THE MVC STRUCTURE
	@PostMapping(value="uploadRecipe" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE
			             /* ,produces = "application/json"*/)
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> uploadRecipe(@RequestParam("file") MultipartFile file, 
			                                   @RequestParam("recipeName")  String recipeName,
			                                   @RequestParam("cookingTime") String cookingTime,
			                                   @RequestParam("description") String description,
			                                   Authentication authentication) {
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());	
		try {
			   if (fileName.contains("..")) 
	           throw new FileStorageException("Sorry! Filename contains invalid path sequence " 
			                                    + fileName);
	           if(file.isEmpty()) 
	           throw new FileNotFoundException("Failed to store empty file " + fileName);
	           
	          // for finding The User Who is Uploading the Recipe
	          User user=userService.findByUserName(authentication.getName());
	            
			RecipeDetail recipeDetail=new RecipeDetail();
			// Set The User Id As Foriegn Key
			recipeDetail.setUserId(user.getUserId());
			recipeDetail.setRecipeName(recipeName);
			recipeDetail.setCookingTime(cookingTime);
			recipeDetail.setCookingTime(cookingTime);
			recipeDetail.setDescription(description);
			recipeDetail.setFilepath("f://userPictures//");// Dougth Here
			Date obj = new Date();
			recipeDetail.setRecipeRegisterDate(obj);
			recipeDetail.setFileName(fileName);
			recipeDetail.setFileType(file.getContentType());
			
			RecipeDetail theRecipeDetail=recipeService.saveRecipeDetail(recipeDetail);
			 
			 File convertFile = new File("f://userPictures//"+recipeDetail.getId()+".jpg");
				convertFile.createNewFile();
				FileOutputStream fout = new FileOutputStream(convertFile);
				if(file != null)
				fout.write(file.getBytes());
				fout.close();
			
				
			
		}
		catch(Exception ex) {
		throw new FileStorageException("Could not store file " + fileName + ". "
				                        + "Please try again!", ex);
			
		}
		return new ResponseEntity<>("File is Uploaded Sucessfully",HttpStatus.OK);
		
	}
		
	
	

    
}
