package com.mcommandes.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommandeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 5802887581912844474L;

	public CommandeNotFoundException(String message) {
        super(message);
    }
}
