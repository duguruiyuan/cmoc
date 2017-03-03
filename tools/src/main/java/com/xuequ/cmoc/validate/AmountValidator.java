package com.xuequ.cmoc.validate;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AmountValidator implements ConstraintValidator<Amount, Double>{

	Pattern pattern = Pattern.compile("^(([0-9]{1,10})|([0]{1}))(\\.(\\d){0,2})?$");
	public void initialize(Amount constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	public boolean isValid(Double value, ConstraintValidatorContext context) {
		if (value == null)
			return true;
		DecimalFormat df = new DecimalFormat("0.######");//最少位就用0来确定
		String s=df.format(value);
		Matcher m = pattern.matcher(s.trim());
		return m.matches();
	}

}
