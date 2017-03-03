package com.xuequ.cmoc.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdcardValidator implements ConstraintValidator<Idcard, String>{
	Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");

	public void initialize(Idcard idcard) {

	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null)
			return true;
		Matcher m = idNumPattern.matcher(value.trim());
		return m.matches();
	}

}
