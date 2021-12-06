package com.vivo.kafkaproduce.adapter.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractBusinessException extends Exception{

	private static final long serialVersionUID = -1100732234292037401L;
	private String razao;
    private int codigo;	

	public AbstractBusinessException(int code, String razao) {

    	super(razao);
        this.setCodigo(code);
		this.setRazao(razao);
		
	}
	
	
	
	
	public static ResponseEntity<ExceptionResponse> ErroHandlerAbstractBusinessException(AbstractBusinessException e) {
		ExceptionResponse response = new ExceptionResponse(e.getCodigo(),e.getRazao());
		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.valueOf(e.getCodigo()));
	}	


	
	



	
}
