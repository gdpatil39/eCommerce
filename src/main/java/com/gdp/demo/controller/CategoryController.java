package com.gdp.demo.controller;

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
import com.gdp.demo.dtos.CategoryDto;
import com.gdp.demo.dtos.PageableResponse;
import com.gdp.demo.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    
	private CategoryService categoryService;
	
	//create
	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto ){
		
		CategoryDto saveCategoryDto = categoryService.create(categoryDto);
		
		return new ResponseEntity<>(saveCategoryDto, HttpStatus.CREATED);
		
	}
	
	
	//update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable String categoryId ){
		
		CategoryDto updateCategoryDto = categoryService.update(categoryDto,categoryId);
		
		return new ResponseEntity<>(updateCategoryDto, HttpStatus.OK);
		
	}
	
	//delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponseMessage> deleteCategory(@PathVariable String categoryId ){
		categoryService.delete(categoryId);
		ApiResponseMessage apiResponseMessage = ApiResponseMessage.builder().message("").status(HttpStatus.OK).success(true).build();
		
		return new ResponseEntity<>(apiResponseMessage, HttpStatus.OK);
		
	}

	//get all
	
	public ResponseEntity<PageableResponse<CategoryDto>> getAll(
			@RequestParam(value="pageNumber",defaultValue = "0",required = false) int pageNumber,
			@RequestParam(value="pageSize",defaultValue="0",required = false) int pageSize,
			@RequestParam(value="sortBy",defaultValue="title",required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue="asc",required = false)String sortDir
			){
		PageableResponse<CategoryDto> pageableResponse= categoryService.getAll(pageNumber,pageSize , sortBy, sortDir);
		 return new ResponseEntity<>(pageableResponse,HttpStatus.OK);
	}
	
	
	//get single
	
	
	
}
