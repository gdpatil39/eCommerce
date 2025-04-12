package com.gdp.demo.services;

import com.gdp.demo.dtos.CategoryDto;
import com.gdp.demo.dtos.PageableResponse;

public interface CategoryService {

	//create
	CategoryDto create(CategoryDto categoryDto);
	
	//update
	CategoryDto update(CategoryDto categoryDto, String categoryId);
	
	//delete
	void delete(String categoryId);
	
	//get all
	PageableResponse<CategoryDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir);
	
	//get single category detail
	CategoryDto get(String categoryId);
	//search
	
	
}
