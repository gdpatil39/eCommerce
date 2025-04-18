package com.gdp.demo.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gdp.demo.dtos.ApiResponseMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private Logger logger =LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponseMessage> rsourceNotFoundExceptionHander(ResourceNotFoundException ex){
		
		logger.info("Exception Handler invoked !!");
		ApiResponseMessage response = ApiResponseMessage.builder().message(ex.getMessage()).status(HttpStatus.NOT_FOUND).success(true).build();
		
		return new ResponseEntity(response,HttpStatus.NOT_FOUND);
		
	
	}
	
	
	//MethodArgumentNotValidException
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValidException(org.springframework.web.bind.MethodArgumentNotValidException ex){
		
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		Map<String, Object> responce = new HashMap<>();
		allErrors.stream().forEach(ObjectError -> {
			String message=ObjectError.getDefaultMessage();
			String field = ((FieldError)ObjectError).getField();
			responce.put(field, message);
		
		});
		
	
		return new ResponseEntity<>(responce, HttpStatus.BAD_REQUEST);
		
		
	}
	
	// handle bad api exception 
	@ExceptionHandler(BadApiRequest.class)
	public ResponseEntity<ApiResponseMessage> handleBadApiRequest(BadApiRequest ex){
		
		logger.info("bad api request !!");
		ApiResponseMessage response = ApiResponseMessage.builder().message(ex.getMessage()).status(HttpStatus.BAD_REQUEST).success(false).build();
		
		return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
		
	
	}
}
