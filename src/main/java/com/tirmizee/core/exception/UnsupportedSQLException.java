package com.tirmizee.core.exception;

public class UnsupportedSQLException extends RuntimeException {

	private static final long serialVersionUID = 4027091698238074899L;
	
	public UnsupportedSQLException() {
        super();
    }
	
	public UnsupportedSQLException(String message){
		super(message);
	}
	
	public UnsupportedSQLException(Throwable cause) {
        super(cause);
    }
	
}
