package com.project.bankmanag.services.account;

import java.util.List;

import com.project.bankmanag.models.Account;

public interface AccountService {
	List<Account> getAll();

	Account getAccountById(Long id);

	Account addAccount(Account account);

	Account updateAccount(Account accountToUpdate);

	void deleteAccount(Long id);
}
