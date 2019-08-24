package com.flairstech.Exceptions;

public class DownConnectionException extends RuntimeException{
	
	public DownConnectionException(String msg) {
        super(msg);
    }
}
