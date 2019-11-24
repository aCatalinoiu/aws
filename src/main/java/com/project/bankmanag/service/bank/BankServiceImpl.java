package com.project.bankmanag.service.bank;

import com.project.bankmanag.exception.BankException;
import com.project.bankmanag.exception.BankNotFoundException;
import com.project.bankmanag.model.Bank;
import com.project.bankmanag.repository.BankRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final Logger logger = LoggerFactory.getLogger(BankService.class);

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public Page<Bank> getAll(Pageable pageable) {
        return bankRepository.findAll(pageable);
    }

    @Override
    public Bank getClientById(Long id) {
        return bankRepository.findById(id).orElseThrow(() -> new BankException(id));
    }

    @Override
    public Bank addClient(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public Bank updateClient(Bank bankToUpdate, Long id) {
        bankRepository.findById(id)
                .orElseThrow(() -> new BankNotFoundException("Cannot find bank with id: " + id));
        bankToUpdate.setId(id);
        return bankRepository.save(bankToUpdate);
    }

    @Override
    public void deleteClient(Long id) {
        bankRepository.findById(id).orElseThrow(
                () -> new BankNotFoundException("Cannot find bank with id: " + id));
        bankRepository.deleteById(id);
    }
}
