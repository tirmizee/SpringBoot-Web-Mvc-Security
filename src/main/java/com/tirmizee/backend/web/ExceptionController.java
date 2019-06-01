package com.tirmizee.backend.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tirmizee.backend.web.data.MessageError;
import com.tirmizee.core.component.VarargMessageSource;
import com.tirmizee.core.exception.BusinessException;
import com.tirmizee.core.exception.UrlNotFoundException;

@ControllerAdvice
public class ExceptionController {
	
	public final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);
	
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
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handleNotFoundException(NoHandlerFoundException ex, RedirectAttributes redirectAttr){
		redirectAttr.addFlashAttribute("url", ex.getRequestURL());
		return new ModelAndView("redirect:/NotFound");
	}
	
	@ExceptionHandler(UrlNotFoundException.class)
	public ModelAndView handleNotFoundUrlNotFoundException(UrlNotFoundException ex, RedirectAttributes redirectAttr){
		redirectAttr.addFlashAttribute("url", ex.getRequestURL());
		return new ModelAndView("redirect:/NotFound");
	}

}
