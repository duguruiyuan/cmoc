/**
 * XueChengJF.com Inc.
 * Copyright (c) 2016-2026 All Rights Reserved.
 */
package com.xuequ.cmoc.validate;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <pre>
 * 金额校验(适用于BigDecimal类型)
 * </pre>
 *
 * @author zhangshihai
 * @version $Id: MoneyValidator.java, 2016年3月21日 上午9:44:07 zhangshihai Exp $
 */
public class MoneyValidator implements ConstraintValidator<Money, BigDecimal>{
	Pattern pattern = Pattern.compile("^(([0-9]{1,10})|([0]{1}))(\\.(\\d){0,2})?$");
	/** 
	 * @param paramA
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(Money paramA) {
	}

	/** 
	 * @param paramT
	 * @param paramConstraintValidatorContext
	 * @return
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(BigDecimal paramT, ConstraintValidatorContext paramConstraintValidatorContext) {
		if (paramT == null)
			return true;
		DecimalFormat df = new DecimalFormat("0.######");//最少位就用0来确定
		String s=df.format(paramT);
		Matcher m = pattern.matcher(s.trim());
		return m.matches();
	}

}
