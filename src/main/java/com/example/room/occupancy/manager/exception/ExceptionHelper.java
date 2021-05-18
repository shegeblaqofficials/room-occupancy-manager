package com.example.room.occupancy.manager.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHelper.class);
	
	@ExceptionHandler(value = {InvalidInputException.class})
	public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex){
		logger.error("InvalidInputException: ", ex.getMessage());
		ErrorResponse error = new ErrorResponse("400",ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {UnauthorizedException.class})
	public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex){
		logger.error("UnauthorizedException: ", ex.getMessage());
		ErrorResponse error = new ErrorResponse("401",ex.getMessage());
		return new ResponseEntity<Object>(error, HttpStatus.UNAUTHORIZED);
	}

	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleUnknownException (Exception ex){
		logger.error("Exception: ", ex.getMessage());
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
