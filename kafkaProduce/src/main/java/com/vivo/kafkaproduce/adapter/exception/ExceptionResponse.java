package com.vivo.kafkaproduce.adapter.exception;


import lombok.Data;

@Data
public class ExceptionResponse {



	private int status_code;
	private String message;


	public ExceptionResponse(int status_code, String message) {
		this.status_code = status_code;
		this.message = message;
	}

}
