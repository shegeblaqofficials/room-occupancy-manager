package com.example.room.occupancy.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -513739766737059635L;

	public UnauthorizedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
