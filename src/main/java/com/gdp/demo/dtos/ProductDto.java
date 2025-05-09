package com.gdp.demo.dtos;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDto {

	private String productId;
	private String title;
	
	private String description;
	private int price;
	private int dicountedPrice;
	private int quantity;
	private Date addedDate;
	private boolean live;
	private boolean stock;
}
