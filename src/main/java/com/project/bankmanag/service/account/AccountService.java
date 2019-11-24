package com.project.bankmanag.service.account;

import com.project.bankmanag.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {

    Page<Account> getAll(Pageable pageable);

    Account getAccountById(Long id);

    Account addAccount(Account account);

    Account updateAccount(Account accountToUpdate, Long id);

    void deleteAccount(Long id);
}
