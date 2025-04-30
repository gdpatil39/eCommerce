package com.gdp.demo.controller;

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
import com.gdp.demo.dtos.PageableResponse;
import com.gdp.demo.dtos.ProductDto;
import com.gdp.demo.services.ProductService;

import lombok.experimental.PackagePrivate;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
		ProductDto createdProduct = productService.create(productDto);

		return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);

	}

	// UPDATE
	@PutMapping("/{productId}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId,
			@RequestBody ProductDto productDto) {
		ProductDto updatedProduct = productService.update(productDto, productId);

		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);

	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<ApiResponseMessage> delete(@PathVariable String productId) {
		productService.delete(productId);
	
		ApiResponseMessage responseMessage = ApiResponseMessage.builder().message("Product is successfully deleted").status(HttpStatus.OK).success(true).build();
		
		return new ResponseEntity<>(responseMessage,HttpStatus.OK);
	
	
	}

	//get single
	@GetMapping("/{productId}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable String productId){
		ProductDto productDto = productService.get(productId);
		
		return new ResponseEntity<>(productDto,HttpStatus.OK);
		
	}
	
	
	//get all
	@GetMapping()
	public ResponseEntity<PageableResponse<ProductDto>> getAll(
			@RequestParam(value="pageNumber",defaultValue = "0",required = false) int pageNumber,
			@RequestParam(value="pageSize",defaultValue="10",required = false) int pageSize,
			@RequestParam(value="sortBy",defaultValue="title",required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue="asc",required = false)String sortDir
			
			){
		
		
		PageableResponse<ProductDto> pageableResponse = productService.getAll(pageNumber, pageSize, sortBy, sortDir);
		
		
		return new ResponseEntity<>(pageableResponse,HttpStatus.OK);
		
		
		
	} 
	//get all live
	
	@GetMapping("/live")
	public ResponseEntity<PageableResponse<ProductDto>> getAllLive(
			@RequestParam(value="pageNumber",defaultValue = "0",required = false) int pageNumber,
			@RequestParam(value="pageSize",defaultValue="10",required = false) int pageSize,
			@RequestParam(value="sortBy",defaultValue="title",required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue="asc",required = false)String sortDir
			){
		
		PageableResponse<ProductDto> allLive = productService.getAllLive(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<>(allLive,HttpStatus.OK);
		
	}
	
	
	//search all
	@GetMapping("/search/{query}")
	public ResponseEntity<PageableResponse<ProductDto>> searchProduct(
			@PathVariable String query,
			@RequestParam(value="pageNumber",defaultValue = "0",required = false) int pageNumber,
			@RequestParam(value="pageSize",defaultValue="10",required = false) int pageSize,
			@RequestParam(value="sortBy",defaultValue="title",required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue="asc",required = false)String sortDir
			){
		
		PageableResponse<ProductDto> allSearchProduct = productService.searchByTitle(query, pageNumber, pageSize, sortBy, sortDir);
		
		return new ResponseEntity<>(allSearchProduct,HttpStatus.OK);
		
	}
	
	
	
}
