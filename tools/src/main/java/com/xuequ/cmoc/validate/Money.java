/**
 * XueChengJF.com Inc.
 * Copyright (c) 2016-2026 All Rights Reserved.
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
 * <pre>
 * 金额校验(适用于BigDecimal类型)
 * </pre>
 *
 * @author zhangshihai
 * @version $Id: Money.java, 2016年3月21日 上午9:42:29 zhangshihai Exp $
 */
@Constraint(validatedBy = { MoneyValidator.class })
@Documented
@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Money {
	String message() default "金额错误！金额总长度（包括两位小数）不能超过12位";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
