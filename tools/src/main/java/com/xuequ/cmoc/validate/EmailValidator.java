package com.xuequ.cmoc.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String>{
	
	Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
	public void initialize(Email constraintAnnotation) {
		
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.equals(""))
			return true;
		Matcher m = pattern.matcher(value.trim());
		return m.matches();
	}

}
