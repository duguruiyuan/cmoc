package com.xuequ.cmoc.validate;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RateValidator implements ConstraintValidator<Rate, Double>{
	Pattern pattern = Pattern.compile("^(([0-9]{1,10})|([0]{1}))(\\.(\\d){0,5})?$");
	
	@Override
	public void initialize(Rate constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context) {
		if (value == null)
			return true;
		DecimalFormat df = new DecimalFormat("0.############");//最少位就用0来确定
		String s=df.format(value);
		Matcher m = pattern.matcher(s.trim());
		return m.matches();
	}

}
