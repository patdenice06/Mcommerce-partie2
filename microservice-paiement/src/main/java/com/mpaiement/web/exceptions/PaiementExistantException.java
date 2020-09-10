package com.mpaiement.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PaiementExistantException extends RuntimeException {
	private static final long serialVersionUID = -391953381740475072L;

	public PaiementExistantException(String message) {
        super(message);
    }
}
