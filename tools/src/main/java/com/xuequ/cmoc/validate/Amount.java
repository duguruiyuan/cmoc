package com.xuequ.cmoc.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Constraint(validatedBy = { AmountValidator.class })
@Documented
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Amount {
	String message() default "金额错误！金额总长度（包括两位小数）不能超过12位";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
