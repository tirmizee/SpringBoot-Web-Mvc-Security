package com.tirmizee.backend.web.data;

import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MessageError implements Serializable{

	private static final long serialVersionUID = -8627851142586617075L;
	
	@JsonFormat(
		shape = JsonFormat.Shape.STRING, 
		pattern = "dd-MM-yyyy hh:mm:ss" )
	private Date timestamp;
	 
	private HttpStatus status;
	private String error;
	private String exception;
	private String message;
	private String path;
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
