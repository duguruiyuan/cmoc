package com.xuequ.cmoc.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * 
 * @author 000000023
 *
 */


@Constraint(validatedBy = { EmailValidator.class })
@Documented
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {
	String message() default "邮箱格式错误！";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
