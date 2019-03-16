package com.tirmizee.core.exception;

public class UrlNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public UrlNotFoundException() {
		super();
	}

	public UrlNotFoundException(String message) {
		super(message);
	}
	
	public UrlNotFoundException(String message,String url) {
		super(message);
	}

	public UrlNotFoundException(Throwable cause) {
		super(cause);
	}

}
