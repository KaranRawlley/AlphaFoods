package com.mind.controller_advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.mind.exception_handling.UserErrorResponce;
import com.mind.exception_handling.UserNotFoundException;



@ControllerAdvice
public class UserExceptionHandler 
{
	@ExceptionHandler
	public ResponseEntity<UserErrorResponce>handleExcption(UserNotFoundException exception){
		// Add An Exception Handler To Catch User Not Found
		UserErrorResponce error=new UserErrorResponce(HttpStatus.NOT_FOUND.value(),
															exception.getMessage(),
															System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<UserErrorResponce>handleAllExcption(Exception exception){
		// Add An Exception Handler To Catch Any Exception
		UserErrorResponce error=new UserErrorResponce(HttpStatus.BAD_REQUEST.value(),
														exception.getMessage(),
														System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
}