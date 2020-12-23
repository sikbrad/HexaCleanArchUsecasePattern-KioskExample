package com.gqshop.kiosk.entrypoint.rest;

public class ErrorMessageDto {
	private final String errorType;
	private final String message;
	public ErrorMessageDto(String errorType, String message) {
		super();
		this.errorType = errorType;
		this.message = message;
	}
	public String getErrorType() {
		return errorType;
	}
	public String getMessage() {
		return message;
	}
	
	
	

}
