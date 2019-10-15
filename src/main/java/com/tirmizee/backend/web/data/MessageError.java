package com.tirmizee.backend.web.data;

import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageError implements Serializable {

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
	
}
