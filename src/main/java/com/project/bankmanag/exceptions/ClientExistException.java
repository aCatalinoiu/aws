package com.project.bankmanag.exceptions;

public class ClientExistException extends RuntimeException {
	public ClientExistException(String cnp){
		super("Client already exists with the given cnp: " + cnp);
	}
}
