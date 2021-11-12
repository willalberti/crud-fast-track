package com.vivo.kafkaproduce.adapter.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class ControllerExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	@ExceptionHandler({ AbstractBusinessException.class })
	public <T> ResponseEntity<T> abstractBusinessException(AbstractBusinessException e) {		
		logger.info("Descricao:{} Obejto:{}","Exception Capturada no ControllerExceptionHandler.class",e.getMessage());
		return (ResponseEntity<T>) AbstractBusinessException.ErroHandlerAbstractBusinessException(e);
	}


}
