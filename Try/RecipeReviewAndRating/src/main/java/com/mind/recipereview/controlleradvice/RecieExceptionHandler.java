package com.mind.recipereview.controlleradvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.mind.recipereview.exceptionhandling.ErrorResponce;
import com.mind.recipereview.exceptionhandling.RecipeNotFoundException;
@RestControllerAdvice
public class RecieExceptionHandler 
{
	@ExceptionHandler
	public ResponseEntity<ErrorResponce>handleExcption(RecipeNotFoundException exception){
		//  An Exception Handler To Catch User Not Found
		ErrorResponce error=new ErrorResponce(HttpStatus.NOT_FOUND.value(),
															exception.getMessage(),
															System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponce>handleAllExcption(Exception exception){
		//  An Exception Handler To Catch Any Exception
		ErrorResponce error=new ErrorResponce(HttpStatus.BAD_REQUEST.value(),
														exception.getMessage(),
														System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
}
