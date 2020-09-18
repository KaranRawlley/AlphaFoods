package com.mind.recipereview.controller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mind.recipereview.entity.RecipeDetail;
import com.mind.recipereview.service.RecipeService;
@RestController
@RequestMapping("/download")
public class RecipeDownloadController {
	
	@Autowired
	RecipeService recipeService;
	
	@Autowired
	private ServletContext servletContext;
	
	@GetMapping(value ="/recipepicture/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Integer id,
    		HttpServletRequest request) throws IOException {
    	
    	RecipeDetail recipeDetail = recipeService.findById(id);
        byte[] imageByte = null;
        HttpHeaders headers = null;
        File file = new File("f:/userPictures/"+recipeDetail.getId()+".jpg");
        MediaType mediaType = getMediaTypeForFileName(this.servletContext, file.getName());
        if (!file.exists())
            return null;

		imageByte = Files.readAllBytes(file.toPath());
        headers = new HttpHeaders();
        headers.setContentType(mediaType);
        return new ResponseEntity<byte[]>(imageByte, headers, HttpStatus.OK);
    	
    }
	
	 public MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
	       String mineType = servletContext.getMimeType(fileName);
	        try {
	            MediaType mediaType = MediaType.parseMediaType(mineType);
	            return mediaType;
	        } 
	        catch (Exception e) {
	            return MediaType.APPLICATION_OCTET_STREAM;
	        }
	    }

}
