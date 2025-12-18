package com.excelr.shopping.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.excelr.shopping.controller.ProductController;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice(assignableTypes = {
	    ProductController.class
	})
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handlePlayerNotFound(RuntimeException ex)
	{ 
			return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<String> handlePlayerNotFound(ResourseNotFoundException ex)
	{ 
			return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<APIError>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex)
	{ 
		List<APIError> errors=new ArrayList();  //[]
		
		for (FieldError error : ex.getBindingResult().getFieldErrors()) 
		{
		APIError apiError = new APIError(error.getDefaultMessage(), error.getField(), error.getRejectedValue());
		errors.add(apiError);
		}

			return new ResponseEntity<List<APIError>>(errors,HttpStatus.NOT_FOUND);
	}
}

/*
[
{
"message" : "Product Title must be between 5 and 100 characters",
"field" : "title",
"rejectedValue" : "my"
},
{
"message" : "Product Price must be greater than 0 Rs",
"field" : "price",
"rejectedValue" : "-1"
}
] 
 */
