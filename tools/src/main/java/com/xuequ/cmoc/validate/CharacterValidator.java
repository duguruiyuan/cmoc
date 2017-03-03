package com.xuequ.cmoc.validate;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CharacterValidator implements ConstraintValidator<Character, String>{
    
	protected int len = 100;
	
	public void initialize(Character constraintAnnotation) {
		len = constraintAnnotation.maxLen();
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value != null){
		int length = value.getBytes().length;
		if(length>len){
			return false;
		}
		}
		return true;
	}

}
