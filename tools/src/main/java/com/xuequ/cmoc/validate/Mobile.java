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
 * @author yanbo
 *
 */
@Constraint(validatedBy = { MobileValidator.class })
@Documented
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Mobile {
	String message() default "手机格式错误！";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
