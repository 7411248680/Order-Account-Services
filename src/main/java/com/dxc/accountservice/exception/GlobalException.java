package com.dxc.accountservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(AccountNumberException.class)
	public ResponseEntity<String>  accountNotFoundException(AccountNumberException accountNumberException) {
		String message=accountNumberException.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}

}
