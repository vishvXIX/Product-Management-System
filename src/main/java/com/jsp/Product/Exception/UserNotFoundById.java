package com.jsp.Product.Exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundById extends RuntimeException {

	private String message ;

	public UserNotFoundById(String message) {
		super();
	}
	
	public UserNotFoundById(HttpStatus notFound) {
		
	}	
	
	public void setMessage(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
