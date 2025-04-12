package com.gdp.demo.services.impl;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.gdp.demo.dtos.CategoryDto;
import com.gdp.demo.dtos.PageableResponse;
import com.gdp.demo.entities.Category;
import com.gdp.demo.exceptions.ResourceNotFoundException;
import com.gdp.demo.helper.Helper;
import com.gdp.demo.repositories.CategoryRepository;
import com.gdp.demo.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public CategoryDto create(CategoryDto categoryDto) {
		Category category = mapper.map(categoryDto, Category.class);
		Category saveCategory = categoryRepository.save(category);
		return mapper.map(saveCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto update(CategoryDto categoryDto, String categoryId) {
		//get category of given id
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		
		//update category details
		category.setTitle(categoryDto.getTitle());
		category.setDescription(categoryDto.getDescription());
		category.setCoverImage(categoryDto.getCoverImage());
		
		Category updateCategory = categoryRepository.save(category);
		
		
		return mapper.map(updateCategory, CategoryDto.class);
	}

	@Override
	public void delete(String categoryId) {
		//get category of given id
				Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
				categoryRepository.delete(category);

	}

	@Override
	public PageableResponse<CategoryDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir) {
	 
		Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
		Pageable pageable = PageRequest.of(pageNumber, pageSize ,sort);
		
		Page<Category> page = categoryRepository.findAll(pageable);
		PageableResponse<CategoryDto> pageableResponse = Helper.getPageableResponse(page, CategoryDto.class);
		return pageableResponse;
	}

	@Override
	public CategoryDto get(String categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		 return mapper.map(category, CategoryDto.class);
	}

}
