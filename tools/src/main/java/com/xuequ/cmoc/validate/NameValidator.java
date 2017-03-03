package com.xuequ.cmoc.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {

	Pattern pattern = Pattern.compile("[\u4E00-\u9FA5]{2,}");
	public void initialize(Name constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.equals(""))
			return true;
		Matcher m = pattern.matcher(value.trim());
		return m.matches();
	}

}
