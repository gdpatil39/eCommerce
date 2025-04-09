package com.gdp.demo.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gdp.demo.dtos.ApiResponseMessage;
import com.gdp.demo.dtos.Userdtos;
import com.gdp.demo.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//create
	@PostMapping
	public ResponseEntity<Userdtos> createUser(@Valid @RequestBody Userdtos userDto){
		
		Userdtos userDto1 = userService.createUser(userDto);
		
		return new ResponseEntity<>(userDto1 ,HttpStatus.CREATED);
		
		
	} 
	
	
		//update
		@PutMapping("/{userId}")
		public ResponseEntity<Userdtos> updateUser(
				@PathVariable("userId") String userId,
				@Valid @RequestBody Userdtos userDto){
		
		Userdtos updateUserDto = userService.updateUser(userDto,userId );
		
		return new ResponseEntity<>(updateUserDto ,HttpStatus.CREATED);
		
		
	} 
		
		//delete
		@DeleteMapping("/{userId}")
		public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId){
			
			userService.deleteUser(userId);
			ApiResponseMessage messsage = ApiResponseMessage
					.builder()
					.message("user is deleted successfully ")
					.success(true)
					.status(HttpStatus.OK)
					.build();
			return new ResponseEntity<>(messsage,HttpStatus.OK );
			
		}
		
	//get all
		@GetMapping
		public ResponseEntity<List<Userdtos>> getAllUser(
				@RequestParam(value="pageNumber",defaultValue ="0", required = false) int pageNumber,
				@RequestParam(value="pageSize",defaultValue ="10", required = false) int pageSize
				){
			
			
			return new ResponseEntity<>(userService.getAllUser(pageNumber,pageSize),HttpStatus.OK);
			
		}
		
		//get single by userId
		@GetMapping("/{userId}")
		public ResponseEntity<Userdtos> getUser(@PathVariable String userId){
			return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
			
			
		}
		
		//get user by email
		@GetMapping("/email/{email}")
		public ResponseEntity<Userdtos> getUserByEmail(@PathVariable String email){
			return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
			
		}
		
		//search user
		@GetMapping("/search/{keywords}")
		public ResponseEntity<List<Userdtos>> searchUser(@PathVariable String keywords){
			return new ResponseEntity<>(userService.searchUser(keywords),HttpStatus.OK);
			
		}
		
		
		
		
		
}
