package com.project.bankmanag.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BankException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BankException(Long id){
	super("Could not find bank " + id);
    }

    public BankException(String err, Throwable throwable){
	super(err,throwable);
    }
}
