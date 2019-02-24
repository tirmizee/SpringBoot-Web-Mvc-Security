package com.tirmizee.backend.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tirmizee.backend.web.data.MessageError;
import com.tirmizee.core.component.VarargMessageSource;
import com.tirmizee.core.exception.BusinessException;

@ControllerAdvice
public class WebExceptionController {
	
	public final Logger LOG = Logger.getLogger(WebExceptionController.class);
	
	@Autowired
	private VarargMessageSource messageSource;
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handleBussinesException(BusinessException ex){
		final String message = messageSource.getVargMessage(ex.getCode(), ex.getArgs());
		MessageError messageError = new MessageError();
		messageError.setException(ex.getSuppressed().getClass().getName());
		messageError.setMessage(message);
		messageError.setStatus(ex.getStatus());
		return new ResponseEntity<>(messageError, ex.getStatus());
	}

}
