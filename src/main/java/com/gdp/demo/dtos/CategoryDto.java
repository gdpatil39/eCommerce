package com.gdp.demo.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

	private String categoryId;
	
	@NotBlank
	@Min(value=4,message="title must be of minimum 4 charecteres")
	private String title;
	@NotBlank(message="Decription is required")
	private String description;
	@NotBlank(message=" Cover_image is required")
	private  String coverImage;
}
