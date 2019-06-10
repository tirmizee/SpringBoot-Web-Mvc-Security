package com.tirmizee.core.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -2258151025213080895L;
	
	private String code;
	private Object args[];
	private HttpStatus status;
	
	public BusinessException(String code, Object...args) {
		this.code = code;
		this.args = args;
		this.status = HttpStatus.BAD_REQUEST;
	}
	
	public BusinessException(HttpStatus status, String code, Object...args) {
		this.code = code;
		this.args = args;
		this.status = status;
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

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
}
