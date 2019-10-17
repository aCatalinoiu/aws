package com.project.bankmanag.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import exceptions.ClientNotFoundException;

@ControllerAdvice
public class ClientNotFoundAdvice {
	  @ResponseBody
	  @ExceptionHandler(ClientNotFoundException.class)
	  @ResponseStatus(HttpStatus.NOT_FOUND)
	  String employeeNotFoundHandler(ClientNotFoundException ex) {
	    return ex.getMessage();
	  }
}
