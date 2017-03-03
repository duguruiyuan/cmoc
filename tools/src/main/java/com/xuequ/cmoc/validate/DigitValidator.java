package com.xuequ.cmoc.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description 	数值校验器
 * @author			wally
 */
public class DigitValidator implements ConstraintValidator<Digit, Numberl> {

	protected Double min = 0d;

	protected Double max = 1.7976931348623157E308;

	protected Integer len = 5;

	protected boolean sync = false;

	public boolean isValid(Numberl value, ConstraintValidatorContext context) {
		if (value == null)
			return true;
		Double v = new Double(value.toString());
		if (v < min || v > max) {
			return false;
		}

		// 不考虑小数位
		String str = v.toString();
		if (str.split("[.]")[0].length() > len) {
			return false;
		}

		sync = false;

		return true;
	}

	@Override
	public void initialize(Digit constraintAnnotation) {
		if (!sync) {
			String type = constraintAnnotation.type();

			if (type.equalsIgnoreCase("short")) {
				max = 32767d;
			} else if (type.equalsIgnoreCase("int")) {
				max = 2147483647d;
			} else if (type.equalsIgnoreCase("long")) {
				max = 9223372036854775807d;
			}

			len = constraintAnnotation.len();

			sync = true;
		}
	}

}
