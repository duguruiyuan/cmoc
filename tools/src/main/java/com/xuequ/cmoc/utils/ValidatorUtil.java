package com.xuequ.cmoc.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public class ValidatorUtil {

    private static Validator validator = Validation.buildDefaultValidatorFactory().
    		getValidator(); 
    
    /**
     * 参数校验
     * @author 000001436
     * @param obj
     * @return
     */
    public static String validatorParams(Object obj) {
		//进行请求参数校验
        Set<ConstraintViolation<Object>> taskSet = validator.validate(obj);
        if(!taskSet.isEmpty()){
        	//返回校验错误的,日志不做校验的记录，直接返回调用方
        	String requestMsg = "";
            for (ConstraintViolation<?> constraintViolation : taskSet){
            	if(requestMsg.equals(""))
            		requestMsg = constraintViolation.getMessage();
            	else
            		requestMsg = requestMsg +"|"+constraintViolation.getMessage();
            }   
            return "数据校验错误:" + requestMsg;
        }
        return null;
	}
    
}
