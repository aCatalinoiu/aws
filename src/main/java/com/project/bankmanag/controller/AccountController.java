package com.project.bankmanag.controller;

import com.project.bankmanag.dto.AccountDTO;
import com.project.bankmanag.mapper.AccountMapper;
import com.project.bankmanag.model.Account;
import com.project.bankmanag.service.account.AccountService;
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
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    @ApiOperation("Find all Accounts")
    @ApiResponses(value = @ApiResponse(code = 200, message = "List of accounts received successfully"))
    public ResponseEntity<Page<AccountDTO>> findAll(Pageable pageable) {
        Page<AccountDTO> accountDTOPage = accountService.getAll(pageable)
                .map(AccountMapper::toAccountDTO);
        return ResponseEntity.status(HttpStatus.OK).body(accountDTOPage);
    }

    @PostMapping
    @ApiOperation("Add new Account")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Account created successfully"),
            @ApiResponse(code = 409, message = "An account already exists with same IBAN"),
            @ApiResponse(code = 400, message = "Field validation failed")})
    public ResponseEntity<AccountDTO> addNewAccount(@RequestBody @Valid Account newAccount) {
        AccountDTO expectedAccountDTO = AccountMapper.toAccountDTO(accountService.addAccount(newAccount));
        return ResponseEntity.status(HttpStatus.CREATED).body(expectedAccountDTO);
    }

    @GetMapping("/{accountId}")
    @ApiOperation("Find Account by Id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Account found"),
            @ApiResponse(code = 404, message = "Could not find account")})
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long accountId) {
        Account expectedAccount = accountService.getAccountById(accountId);
        return expectedAccount != null ?
                ResponseEntity.status(HttpStatus.OK).body(AccountMapper.toAccountDTO(expectedAccount)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{accountId}")
    @ApiOperation("Update Account")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Account updated successfully"),
            @ApiResponse(code = 404, message = "Could not find account to update"),
            @ApiResponse(code = 400, message = "Field validation failed")})
    public ResponseEntity<AccountDTO> updateAccount(@RequestBody @Valid Account newAccount, @PathVariable Long accountId) {
        Account updatedAccount = accountService.updateAccount(newAccount, accountId);
        return updatedAccount != null ?
                ResponseEntity.status(HttpStatus.OK).body(AccountMapper.toAccountDTO(updatedAccount)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{accountId}")
    @ApiOperation("Delete Account by Id")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Account deleted successfully"),
            @ApiResponse(code = 404, message = "Could not find account to delete")})
    public ResponseEntity<Void> removeAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
