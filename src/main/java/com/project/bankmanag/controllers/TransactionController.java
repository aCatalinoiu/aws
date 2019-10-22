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

import com.project.bankmanag.exceptions.transaction.TransactionNotFoundException;
import com.project.bankmanag.models.Transaction;
import com.project.bankmanag.services.transaction.TransactionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
private TransactionService transactionService;
	
	@Autowired
	public void setTransactionService(TransactionService transactionService){
		this.transactionService = transactionService;
	}
	
	@GetMapping
	@ApiOperation("Find all Transactions")
	@ApiResponses(value = @ApiResponse(code = 200, message = "List of Transactions received successfully"))
	List<Transaction> findAll() {
        return transactionService.getAll();
	 } 
	
	@PostMapping
	@ApiOperation("Add new Transaction")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Transaction created successfully"), @ApiResponse(code = 400, message = "A Transaction already exists with same CNP")})
	ResponseEntity<Long> newTransaction(@RequestBody @Valid Transaction newTransaction) {
		Long transactionId = transactionService.addTransaction(newTransaction).getId();
		if(transactionId.equals(null)){
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		} else return new ResponseEntity<>(transactionId, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	@ApiOperation("Find Transaction by Id")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Transaction found"),@ApiResponse(code = 404, message = "Could not find Transaction")})
	Transaction getTransactionById(@PathVariable Long id){
		return transactionService.getTransactionById(id);
	}
	
	@PutMapping("{id}")
	@ApiOperation("Update Transaction")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Transaction updated successfully"),@ApiResponse(code = 404, message = "Could not find Transaction to update"),@ApiResponse(code = 400, message = "Field validation failed")})
	Transaction updateTransaction(@RequestBody @Valid Transaction newTransaction, @PathVariable Long id){
		return transactionService.updateTransaction(newTransaction, id);
	}
	
	@DeleteMapping("{id}")
	@ApiOperation("Delete Transaction by Id")
	@ApiResponses(value = {@ApiResponse(code = 204, message = "Transaction deleted successfully"),@ApiResponse(code = 404, message = "Could not find Transaction to delete")})
	ResponseEntity<Object> removeTransaction(@PathVariable Long id){
		if(!getTransactionById(id).equals(null)){
			transactionService.deleteTransaction(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else throw new TransactionNotFoundException(id);
	}
}
