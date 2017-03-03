package com.xuequ.cmoc.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberValidator implements ConstraintValidator<Numberl,String>{
	
	Pattern pattern = Pattern.compile("/^[1-9]+\\d*$");

	@Override
	public void initialize(Numberl arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if (value == null)
			return true;
		Matcher m = pattern.matcher(value.trim());
		return m.matches();
	}
}
