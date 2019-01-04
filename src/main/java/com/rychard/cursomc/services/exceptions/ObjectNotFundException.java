package com.rychard.cursomc.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)//resolve o problema do erro 500
public class ObjectNotFundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFundException(String msg, Throwable cause) {
		super(msg,cause);
	}
}
