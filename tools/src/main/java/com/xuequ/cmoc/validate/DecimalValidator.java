package com.xuequ.cmoc.validate;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DecimalValidator implements ConstraintValidator<Decimal, BigDecimal>{

	Pattern pattern = Pattern.compile("^(([0-9]{1,10})|([0]{1}))(\\.(\\d){0,2})?$");

	public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
		if (value == null)
			return true;
		DecimalFormat df = new DecimalFormat("0.######");//最少位就用0来确定
		String s=df.format(value);
		Matcher m = pattern.matcher(s.trim());
		return m.matches();
	}

	@Override
	public void initialize(Decimal constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

}
