package com.mpaiement.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PaiementNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -6166476364863198109L;

	public PaiementNotFoundException(String message) {
		super(message);
	}

	
	
}
