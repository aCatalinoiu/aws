package com.project.bankmanag.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.project.bankmanag.exceptions.ClientExistException;

@ControllerAdvice
public class ClientExistAdvice {
	  @ExceptionHandler(ClientExistException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  String clientExistHandler(ClientExistException ex) {
	    return ex.getMessage();
	  }
}
