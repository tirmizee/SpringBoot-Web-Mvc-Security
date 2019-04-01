package com.tirmizee.core.exception;

public class UrlNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String requestURL;
	
	public UrlNotFoundException() {
		super();
	}

	public UrlNotFoundException(String message) {
		super(message);
	}
	
	public UrlNotFoundException(String message,String requestURL) {
		super(message);
	}

	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}

}
