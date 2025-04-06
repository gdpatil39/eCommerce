package com.gdp.demo.validate;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImageNameValidator implements ConstraintValidator<ImageNameValid,String> {

	
	private Logger logger=LoggerFactory.getLogger(ImageNameValidator.class);
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		//logic for black check
		
		logger.info(" Message From isvalid :{} ", value);
		if(value.isBlank()) {
			return false;
			
			
		}else {
			return true;
		}
		
	
	}

}
