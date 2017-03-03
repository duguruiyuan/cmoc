package com.xuequ.cmoc.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

public class MobileValidator implements ConstraintValidator<Mobile, String>{
	/*
	 * 目前手机号码段范围：
	 * 电信号段:133/153/180/181/189/177；
                  联通号段:130/131/132/155/156/185/186/145/176；
                  移动号段：134/135/136/137/138/139/150/151/152/157/158/159/182/183/184/187/188/147/178。
	 * 
	 */
	Pattern p = Pattern.compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");

	public void initialize(Mobile mobile) {

	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isBlank(value)) {
			return true;
		}
		Matcher m = p.matcher(value.trim());
		return m.matches();
	}

}
