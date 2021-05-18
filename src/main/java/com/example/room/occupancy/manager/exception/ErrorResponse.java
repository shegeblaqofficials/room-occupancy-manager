package com.example.room.occupancy.manager.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
	
	public ErrorResponse(String responseCode, String responseDescription) {
		super();
		this.responseCode = responseCode;
		this.responseStatus = "failed";
		this.responseDescription = responseDescription;
	}
	private String responseCode;
	private String responseStatus;
	private String responseDescription;
	
}
