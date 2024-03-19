package com.jsp.Product.Exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.jsp.Product.utility.ErrorStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	private ErrorStructure structure;
	
	public ApplicationExceptionHandler(ErrorStructure structure) {
		super();
		this.structure = structure;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<ObjectError> allErrors = ex.getAllErrors();
		Map<String, String> messages = new HashMap<>();
		
		allErrors.forEach(error->{
			FieldError fieldError = (FieldError)error;
			messages.put(fieldError.getField(),error.getDefaultMessage() );
		});
		
		return ResponseEntity.badRequest().body(
				structure.setStatus(HttpStatus.BAD_REQUEST.value())
				.setMessage("Invalid Input")
				.setRootCause(messages));
		
	}
	
}
