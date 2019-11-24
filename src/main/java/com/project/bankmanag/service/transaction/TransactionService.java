package com.project.bankmanag.service.transaction;

import com.project.bankmanag.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionService {

    Page<Transaction> getAll(Pageable pageable);

    Transaction getTransactionById(Long id);
	
    Transaction addTransaction(Transaction transaction);

    Transaction updateTransaction(Transaction transactionToUpdate, Long id);

    void deleteTransaction(Long id);
}
