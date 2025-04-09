package com.gdp.demo.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
}
