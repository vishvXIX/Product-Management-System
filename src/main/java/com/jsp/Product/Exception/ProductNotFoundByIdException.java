package com.jsp.Product.Exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundByIdException extends RuntimeException {

	private String message ;

	public ProductNotFoundByIdException(String message) {
		super();
	}
	
	public ProductNotFoundByIdException(HttpStatus notFound) {
		
	}	
	
	public void setMessage(String message) {
		this.message=message;
	}

	public String getMessage() {
		return message;
	}
	
	
}
