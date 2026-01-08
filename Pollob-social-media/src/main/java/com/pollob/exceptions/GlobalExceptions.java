package com.pollob.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

// ei annotation dara spring-security k bolchi je, akhane sob exception handle hobe.
@ControllerAdvice
public class GlobalExceptions {
	
	//User Exception er jonno ( Ei vabe sob class(Ex: Chat, Comment) er jonno separate Exception create korte hobe.
	// User Exception er jonno change korchi (UserController, UserService, UserServiceImplementation)
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetais> UserException(UserException ue, 
			WebRequest req) {
		
		ErrorDetais error = new ErrorDetais(ue.getMessage(), req.getDescription(false), LocalDateTime.now() );
		
		return new ResponseEntity<ErrorDetais>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	// Global(common) Exception (ei Exception dara bujhano hoiche sob class er exception)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetais> otherExceptionHandler(Exception ue, 
			WebRequest req) {
		
		ErrorDetais error = new ErrorDetais(ue.getMessage(), req.getDescription(false), LocalDateTime.now() );
		
		return new ResponseEntity<ErrorDetais>(error, HttpStatus.BAD_REQUEST);
		
	}

}
