package com.vivo.kafkaproduce.adapter.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class ControllerExceptionHandler {

	@Autowired 
	private MessageSource messageSouce;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	@ExceptionHandler({ AbstractBusinessException.class })
	public <T> ResponseEntity<T> abstractBusinessException(AbstractBusinessException e) {		
		logger.info("Descricao:{} Obejto:{}","Exception Capturada no ControllerExceptionHandler.class",e.getMessage());
		return (ResponseEntity<T>) AbstractBusinessException.ErroHandlerAbstractBusinessException(e);
	}


	
	@SuppressWarnings("unchecked")
	@ExceptionHandler({MethodArgumentNotValidException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public <T> ResponseEntity<T> ExceptionHandler(MethodArgumentNotValidException e) {		
		logger.info("Descricao:{} Obejto:{}","Exception Capturada no ControllerExceptionHandler.class",e.getMessage());
		
		List<String> msg = new ArrayList<>();
		
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		fieldErrors.forEach(ex -> {
			msg.add(ex.getField() +" - "+ messageSouce.getMessage(ex, LocaleContextHolder.getLocale()));
			
		});
		
		AbstractBusinessException abe = new AbstractBusinessException(400, "Request inválido: "+ msg.toString());	

		
		return (ResponseEntity<T>) AbstractBusinessException.ErroHandlerAbstractBusinessException(abe);
	}
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler({Exception.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public <T> ResponseEntity<T> ExceptionHandler(Exception e) {		
		logger.info("Descricao:{} Obejto:{}","Exception Capturada no ControllerExceptionHandler.class",e.getMessage());
		
		AbstractBusinessException abe = new AbstractBusinessException(500, "Erro interno"+ e.toString());	

		
		return (ResponseEntity<T>) AbstractBusinessException.ErroHandlerAbstractBusinessException(abe);
	}
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler({MissingServletRequestParameterException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public <T> ResponseEntity<T> ExceptionHandler(MissingServletRequestParameterException e) {		
		logger.info("Descricao:{} Obejto:{}","Exception Capturada no ControllerExceptionHandler.class",e.getMessage());
		
		
		
		AbstractBusinessException abe = new AbstractBusinessException(400, "Request inválido: "+ e.getMessage());	

		
		return (ResponseEntity<T>) AbstractBusinessException.ErroHandlerAbstractBusinessException(abe);
	}	
	
	
	
	
}
