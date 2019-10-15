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

import com.tirmizee.backend.dao.ValidateMessageDao;
import com.tirmizee.backend.web.data.MessageError;
import com.tirmizee.core.component.VarargMessageSource;
import com.tirmizee.core.domain.ValidateMessage;
import com.tirmizee.core.exception.MessageSourceException;
import com.tirmizee.core.exception.MessageException;
import com.tirmizee.core.exception.UrlNotFoundException;
import com.tirmizee.core.utilities.DateUtils;

@ControllerAdvice
public class ExceptionController {
	
	public final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);
	
	@Autowired
	private VarargMessageSource messageSource;
	
	@Autowired
	private ValidateMessageDao validateMessageDao;
	
	@ExceptionHandler(MessageSourceException.class)
	public ResponseEntity<?> handleBussinesException(MessageSourceException ex){
		final String message = messageSource.getVargMessage(ex.getCode(), ex.getArgs());
		MessageError messageError = new MessageError();
		messageError.setMessage(message);
		messageError.setStatus(ex.getStatus());
		messageError.setException(ex.getMessage());
		messageError.setTimestamp(DateUtils.nowTimestamp());
		return new ResponseEntity<>(messageError, ex.getStatus());
	}

	@ExceptionHandler(MessageException.class)
	public ResponseEntity<?> handleBusinessMessageException(MessageException ex){
		MessageError messageError = new MessageError();
		messageError.setError(ex.getCode());
		messageError.setTimestamp(DateUtils.nowTimestamp());
		messageError.setMessage("Not found message from database.");
		
		ValidateMessage validateMessage = validateMessageDao.getByCode(ex.getCode());
		if (validateMessage != null) {
			messageError.setError(validateMessage.getMsgCode());
			messageError.setMessage(validateMessage.getMsgDesc());
		}
		
		return ResponseEntity.ok(messageError);
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
