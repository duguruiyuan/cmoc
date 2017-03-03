package com.xuequ.cmoc.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BankCardValidator implements ConstraintValidator<BankCard, String>{

	Pattern pattern = Pattern.compile("^\\d{10,30}$");
	public void initialize(BankCard constraintAnnotation) {
		// TODO Auto-generated method stub
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null)
			return true;
		Matcher m = pattern.matcher(value.trim());
		return m.matches();
	}

}
