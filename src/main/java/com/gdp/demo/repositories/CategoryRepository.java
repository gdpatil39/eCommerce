package com.gdp.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gdp.demo.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

	
}
