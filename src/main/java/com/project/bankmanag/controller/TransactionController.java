package com.project.bankmanag.controller;

import com.project.bankmanag.dto.TransactionDTO;
import com.project.bankmanag.mapper.TransactionMapper;
import com.project.bankmanag.model.Transaction;
import com.project.bankmanag.service.transaction.TransactionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    @ApiOperation("Find all Transactions")
    @ApiResponses(value = @ApiResponse(code = 200, message = "List of transactions received successfully"))
    public ResponseEntity<Page<TransactionDTO>> findAll(Pageable pageable) {
        Page<TransactionDTO> transactionDTOPage = transactionService.getAll(pageable).map(TransactionMapper::toTransactionDTO);
        return ResponseEntity.status(HttpStatus.OK).body(transactionDTOPage);
    }

    @PostMapping
    @ApiOperation("Add new Transaction")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Transaction created successfully"),
            @ApiResponse(code = 409, message = "Field validation failed")})
    public ResponseEntity<TransactionDTO> addNewTransaction(@RequestBody @Valid Transaction newTransaction) {
        TransactionDTO expectedTransactionDTO = TransactionMapper.toTransactionDTO(transactionService.addTransaction(newTransaction));
        return ResponseEntity.status(HttpStatus.OK).body(expectedTransactionDTO);
    }

    @GetMapping("{transactionId}")
    @ApiOperation("Find Transaction by Id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Transaction found"),
            @ApiResponse(code = 404, message = "Could not find transaction")})
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable Long transactionId) {
        Transaction expectedTransaction = transactionService.getTransactionById(transactionId);
        return expectedTransaction != null ?
                ResponseEntity.status(HttpStatus.OK).body(TransactionMapper.toTransactionDTO(expectedTransaction)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("{transactionId}")
    @ApiOperation("Update Transaction")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Transaction updated successfully"),
            @ApiResponse(code = 404, message = "Could not find transaction to update"),
            @ApiResponse(code = 400, message = "Field validation failed")})
    public ResponseEntity<TransactionDTO> updateTransaction(@RequestBody @Valid Transaction newTransaction, @PathVariable Long transactionId) {
        Transaction updatedTransaction = transactionService.updateTransaction(newTransaction, transactionId);
        return updatedTransaction != null ?
                ResponseEntity.status(HttpStatus.OK).body(TransactionMapper.toTransactionDTO(updatedTransaction)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("{transactionId}")
    @ApiOperation("Delete Transaction by Id")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Transaction deleted successfully"),
            @ApiResponse(code = 404, message = "Could not find transaction to delete"),
            @ApiResponse(code = 400, message = "Field validation failed")})
    public ResponseEntity<Void> removeTransaction(@PathVariable Long transactionId) {
        transactionService.deleteTransaction(transactionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
