package com.hcl.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController

public class GlobalExceptionHandler {
	@ExceptionHandler(value = ProductNotAddedException.class)
	public ResponseEntity<ErrorResponse> handleException(ProductNotAddedException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = ContactPersonInvalidException.class)
	public ResponseEntity<ErrorResponse> handleException(ContactPersonInvalidException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = MobileNumberInvalidException.class)
	public ResponseEntity<ErrorResponse> handleException(MobileNumberInvalidException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = StoreLocationInvalidException.class)
	public ResponseEntity<ErrorResponse> handleException(StoreLocationInvalidException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = StoreNameInvalidException.class)
	public ResponseEntity<ErrorResponse> handleException(StoreNameInvalidException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(ProductNotFoundException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = OrderEmptyException.class)
	public ResponseEntity<ErrorResponse> handleException(OrderEmptyException exception) {
		ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
}
