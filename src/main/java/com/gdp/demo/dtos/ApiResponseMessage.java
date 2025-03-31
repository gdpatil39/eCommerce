package com.gdp.demo.dtos;

import org.springframework.http.HttpStatus;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseMessage {

	private  String message;
	private boolean success;
	private HttpStatus status;
	
}
