package com.vivo.kafkaproduce.adapter.exception;


import lombok.Data;

@Data
public class Error {



	private int status_code;
	private String message;


	public Error(int status_code, String message) {
		this.status_code = status_code;
		this.message = message;
	}

}
