package com.gdp.demo.services.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.gdp.demo.dtos.PageableResponse;
import com.gdp.demo.dtos.ProductDto;
import com.gdp.demo.entities.Product;
import com.gdp.demo.exceptions.ResourceNotFoundException;
import com.gdp.demo.helper.Helper;
import com.gdp.demo.repositories.ProductRepository;
import com.gdp.demo.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public ProductDto create(ProductDto productDto) {
		Product product = mapper.map(productDto, Product.class);
		
		//product Id
		String productId = UUID.randomUUID().toString();
		product.setProductId(productId);
		
		//add date
		product.setAddedDate(new Date());
		
		
		
		Product saveProduct = productRepository.save(product);
		return mapper.map(saveProduct, ProductDto.class);
		
		
		
		 
	}

	@Override
	public ProductDto update(ProductDto productDto, String productId) {
	Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found") );
	product.setTitle(product.getTitle());
	product.setDescription(productDto.getDescription());
	product.setPrice(productDto.getPrice());
	product.setDicountedPrice(productDto.getDicountedPrice());
	product.setQuantity(productDto.getQuantity());
	product.setLive(productDto.isLive());
	product.setStock(productDto.isStock());
	
	
	
	Product updateProduct = productRepository.save(product);
	
	
	
	
	
		return mapper.map(updateProduct, ProductDto.class);
		
	}

	@Override
	public void delete(String productId) {
		Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found") );
		productRepository.delete(product);
	}

	@Override
	public ProductDto get(String productId) {
		Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found") );

		return mapper.map(product, ProductDto.class);
	}

	

	@Override
	public PageableResponse<ProductDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
		
		Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()): (Sort.by(sortBy).ascending());
		Pageable pageable = PageRequest.of(pageNumber, pageSize);  
		Page<Product>page=productRepository.findAll(pageable);
		
		
		
		return Helper.getPageableResponse(page, ProductDto.class);
	}
//live 
	@Override
	public PageableResponse<ProductDto> getAllLive(int pageNumber, int pageSize, String sortBy, String sortDir) {
		Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()): (Sort.by(sortBy).ascending());
		Pageable pageable = PageRequest.of(pageNumber, pageSize);  
		Page<Product>page=productRepository.findByLiveTrue(pageable);
		
		
		
		return Helper.getPageableResponse(page, ProductDto.class);
	
	}

	@Override
	public PageableResponse<ProductDto> searchByTitle(String subTitle, int pageNumber, int pageSize, String sortBy,
			String sortDir) {
		Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()): (Sort.by(sortBy).ascending());
		Pageable pageable = PageRequest.of(pageNumber, pageSize);  
		Page<Product>page=productRepository.findByTitleContaining(subTitle,pageable);
		
		
		
		return Helper.getPageableResponse(page, ProductDto.class);
	
	}

}
