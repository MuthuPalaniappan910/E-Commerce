package com.hcl.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactPersonInvalidException extends Exception {
	private static final long serialVersionUID = 1L;

	public ContactPersonInvalidException(String exception) {
		super(exception);
	}
}