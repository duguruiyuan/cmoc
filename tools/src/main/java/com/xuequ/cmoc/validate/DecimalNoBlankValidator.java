package com.xuequ.cmoc.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DecimalNoBlankValidator implements ConstraintValidator<DecimalNotBlank, Double> {

	@Override
	public void initialize(DecimalNotBlank constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context) {
		if (value != null) {
			return true;
		}
		return false;
	}

}
