package com.project.bankmanag.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bankmanag.exceptions.account.AccountNotFoundException;
import com.project.bankmanag.models.Account;
import com.project.bankmanag.services.account.AccountService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	private AccountService AccountService;
	
	@Autowired
	public void setAccountService(AccountService AccountService){
		this.AccountService = AccountService;
	}
	
	@GetMapping
	@ApiOperation("Find all Accounts")
	@ApiResponses(value = @ApiResponse(code = 200, message = "List of Accounts received successfully"))
	List<Account> findAll() {
        return AccountService.getAll();
	 } 
	
	@PostMapping
	@ApiOperation("Add new Account")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Account created successfully"), @ApiResponse(code = 400, message = "A Account already exists with same CNP")})
	ResponseEntity<Long> newAccount(@RequestBody @Valid Account newAccount) {
		Long AccountId = AccountService.addAccount(newAccount).getId();
		if(AccountId.equals(null)){
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		} else return new ResponseEntity<>(AccountId, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	@ApiOperation("Find Account by Id")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Account found"),@ApiResponse(code = 404, message = "Could not find Account")})
	Account getAccountById(@PathVariable Long id){
		return AccountService.getAccountById(id);
	}
	
	@PutMapping("{id}")
	@ApiOperation("Update Account")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Account updated successfully"),@ApiResponse(code = 404, message = "Could not find Account to update"),@ApiResponse(code = 400, message = "Field validation failed")})
	Account updateAccount(@RequestBody @Valid Account newAccount, @PathVariable Long id){
		return AccountService.updateAccount(newAccount, id);
	}
	
	@DeleteMapping("{id}")
	@ApiOperation("Delete Account by Id")
	@ApiResponses(value = {@ApiResponse(code = 204, message = "Account deleted successfully"),@ApiResponse(code = 404, message = "Could not find Account to delete")})
	ResponseEntity<Object> removeAccount(@PathVariable Long id){
		if(!getAccountById(id).equals(null)){
			AccountService.deleteAccount(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else throw new AccountNotFoundException(id);
	}
}
