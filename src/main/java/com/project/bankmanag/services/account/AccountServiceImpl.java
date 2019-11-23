package com.project.bankmanag.services.account;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bankmanag.exceptions.account.AccountExistException;
import com.project.bankmanag.exceptions.account.AccountNotFoundException;
import com.project.bankmanag.models.Account;
import com.project.bankmanag.repositories.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
    private Logger LOG = LoggerFactory.getLogger(AccountService.class);
    private AccountRepository accountRepository;

    @Autowired
    public void setAccountRepository(AccountRepository AccountRepository) {
	this.accountRepository = AccountRepository;
    }

    @Override
    public List<Account> getAll() {
	return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long id) {
	return accountRepository.findById(id)
			.orElseThrow(() -> new AccountNotFoundException(id));
    }

    @Override
    public Account addAccount(Account account) {
	return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Account accountToUpdate) {
	final Optional<Account> foundAccount = accountRepository.findById(accountToUpdate.getId());
	if (foundAccount.isPresent()) {
	    return accountRepository.save(accountToUpdate);
	} else
	    throw new AccountNotFoundException(accountToUpdate.getId());
    }

    @Override
    public void deleteAccount(Long id) {
	final Optional<Account> foundAccount = accountRepository.findById(id);
	if (foundAccount.isPresent()) {
	    accountRepository.delete(foundAccount.get());
	} else
	    throw new AccountNotFoundException(id);
    }
}
