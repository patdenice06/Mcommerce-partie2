package com.clientui.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductBadRequestException extends Exception {
	private static final long serialVersionUID = -1501388690952082971L;

	public ProductBadRequestException(String message) {
		super(message);
	}
}
