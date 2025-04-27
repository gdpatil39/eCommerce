package com.gdp.demo.services;

import java.util.List;

import com.gdp.demo.dtos.PageableResponse;
import com.gdp.demo.dtos.ProductDto;

public interface ProductService {

	
	
	ProductDto create(ProductDto productDto);
	
	ProductDto update(ProductDto productDto,String productId);
	
	void delete(String productId);
	
	ProductDto get(String producId);
	
	PageableResponse<ProductDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir);
	
	PageableResponse<ProductDto>getAllLive(int pageNumber,int pageSize,String sortBy,String sortDir);
	
	
	PageableResponse<ProductDto> searchByTitle(String subTitle,int pageNumber,int pageSize,String sortBy,String sortDir);
	
	
}
