package com.project.bankmanag.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ClientNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ClientNotFoundException(Long id){
		super("Could not find client " + id);
	}
}
