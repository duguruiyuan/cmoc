package com.xuequ.cmoc.exception;

public class ExpirationException extends RuntimeException {
	
	private static final long serialVersionUID = -5864563971079269462L;
	
	public ExpirationException()  {}     
	
    public ExpirationException(String message) {      
        super(message);
    }

}
