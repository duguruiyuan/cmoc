package com.xuequ.cmoc.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = { DigitValidator.class })
@Documented
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Digit {
	String type() default "int";

	int len() default 5; // 整数位长度申明

	String message() default "数字数值超出数字对应类型的限制";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
