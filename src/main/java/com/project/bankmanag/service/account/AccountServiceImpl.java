package com.project.bankmanag.service.account;

import com.project.bankmanag.exception.account.AccountNotFoundException;
import com.project.bankmanag.model.Account;
import com.project.bankmanag.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final Logger logger = LoggerFactory.getLogger(AccountService.class);

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Page<Account> getAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
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
    public Account updateAccount(Account accountToUpdate, Long id) {
        accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
        accountToUpdate.setId(id);
        return accountRepository.save(accountToUpdate);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.findById(id).orElseThrow(
                () -> new AccountNotFoundException(id));
        accountRepository.deleteById(id);
    }
}
