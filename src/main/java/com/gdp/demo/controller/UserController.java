package com.gdp.demo.controller;

import org.springframework.http.MediaType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gdp.demo.dtos.ApiResponseMessage;
import com.gdp.demo.dtos.ImageResponse;
import com.gdp.demo.dtos.PageableResponse;
import com.gdp.demo.dtos.Userdtos;
import com.gdp.demo.services.FileService;
import com.gdp.demo.services.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private FileService fileService;
	
	@Value("${user.profile.image.path}")
	private String imageUploadPath;
	
	private Logger logger=LoggerFactory.getLogger(UserController.class)	;
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
		public ResponseEntity<PageableResponse<Userdtos>> getAllUser(
				@RequestParam(value="pageNumber",defaultValue ="0", required = false) int pageNumber,
				@RequestParam(value="pageSize",defaultValue ="10", required = false) int pageSize,
				
				@RequestParam(value="sortBy",defaultValue ="name", required = false) String sortBy,
				@RequestParam(value="sortDir",defaultValue ="asc", required = false)String sortDir
				
				){
			
			
			return new ResponseEntity<>(userService.getAllUser(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
			
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
		
		//upload user image
		@PostMapping("/image/{userId}")
		public ResponseEntity<ImageResponse>uploadUserImage(@RequestParam("userImage") MultipartFile image,@PathVariable String userId) throws IOException{
			
			
			String imageName =fileService.uploadFile(image, imageUploadPath);
			
			
			//updating user_image_name in user table
			Userdtos user = userService.getUserById(userId);
			user.setImageName(imageName);
			Userdtos userDto = userService.updateUser(user, userId);
			
			
			ImageResponse imageResponse=ImageResponse.builder().imageName(imageName).success(true).status(HttpStatus.CREATED).build();
			return new ResponseEntity<>(imageResponse,HttpStatus.CREATED);
			
			
		}
		
		//serve user image 
		@GetMapping("/image/{userId}")
		public void serveUserImage(@PathVariable String userId ,HttpServletResponse response) throws IOException {
			
			Userdtos user=userService.getUserById(userId);
			logger.info("User image name :{} ",user.getImageName());
		
			InputStream resource = fileService.getResource(imageUploadPath, user.getImageName());
			
			response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			
			StreamUtils.copy(resource, response.getOutputStream());
		}
		
		
		
		
		
		
}
