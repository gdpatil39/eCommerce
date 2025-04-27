package com.gdp.demo.repositories;
import com.gdp.demo.entities.Product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product,String> {

	
	Page<Product> findByLiveTrue(Pageable pageable);
	Page<Product> findByTitleContaining(String subtitle,Pageable pageable);
	
	
}
