package com.clientui.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {// Exception
	private static final long serialVersionUID = -4427411882444691260L;

	public ProductNotFoundException(String message) {
		super(message);
	}
	
	

}
