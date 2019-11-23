package com.project.bankmanag.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BankNotFoundException extends RuntimeException {

    public BankNotFoundException(String err) {
        super(err);
    }
}
