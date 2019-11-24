package com.project.bankmanag.service.transaction;

import com.project.bankmanag.exception.transaction.TransactionNotFoundException;
import com.project.bankmanag.model.Transaction;
import com.project.bankmanag.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final Logger LOG = LoggerFactory.getLogger(TransactionService.class);
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Page<Transaction> getAll(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction updateTransaction(Transaction transactionToUpdate, Long id) {
        transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
        transactionToUpdate.setId(id);
        return transactionRepository.save(transactionToUpdate);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException(id));
        transactionRepository.deleteById(id);
    }
}
