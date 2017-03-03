package com.xuequ.cmoc.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BankCodeValidator implements ConstraintValidator<IsBankCode, String>{
	Pattern idNumPattern = Pattern.compile("^[A-Za-z]+$");

	public void initialize(IsBankCode idcard) {

	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null)
			return true;
		Matcher m = idNumPattern.matcher(value.trim());
		return m.matches();
	}

}
