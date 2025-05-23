package com.gdp.demo.helper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import com.gdp.demo.dtos.PageableResponse;
import com.gdp.demo.dtos.Userdtos;
import com.gdp.demo.entities.User;

public class Helper {

	public static <V,U> PageableResponse<V> getPageableResponse(Page<U> page, Class<V> type){
		

		List<U> entity = page.getContent();

		
		 List<V> allUserdtosList = entity.stream().map(object -> new ModelMapper().map(object, type)).collect(Collectors.toList()); 
		 
		 PageableResponse<V> response= new PageableResponse<>();
		 response.setContent(allUserdtosList);
		 response.setPageNumber(page.getNumber());
		 response.setPageSize(page.getSize());
		 response.setTotalElements(page.getTotalElements());
		 response.setTotalpage(page.getTotalPages());
		 
		 response.setLastPage(page.isLast());
		
		
		
		return response;
		
		
	}
}
