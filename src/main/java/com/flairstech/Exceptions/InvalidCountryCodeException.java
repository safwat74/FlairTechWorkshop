package com.flairstech.Exceptions;

public class InvalidCountryCodeException extends RuntimeException{
	
	public InvalidCountryCodeException(String msg) {
        super(msg);
    }
}
