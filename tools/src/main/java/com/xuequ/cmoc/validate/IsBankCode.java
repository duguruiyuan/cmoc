/**
 * 
 */
package com.xuequ.cmoc.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author 000000959
 *
 */
@Constraint(validatedBy = { BankCodeValidator.class })
@Documented
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsBankCode {
	String message() default "身份证格式错误！请填写正确的身份证号。";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}