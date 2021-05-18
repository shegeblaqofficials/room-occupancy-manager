package com.example.room.occupancy.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5211874928557115446L;

	public InvalidInputException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
