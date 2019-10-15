package com.tirmizee.core.exception;

public class MessageException extends RuntimeException {

	private static final long serialVersionUID = -4258548441179390762L;
	
	private String code;
	private Object args[];
	
	public MessageException(String code) {
		this.code = code;
	}
	public MessageException(String code, Object[] args) {
		this.code = code;
		this.args = args;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}

}
