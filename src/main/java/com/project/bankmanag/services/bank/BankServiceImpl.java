package com.project.bankmanag.services.bank;

import com.project.bankmanag.exceptions.BankException;
import com.project.bankmanag.exceptions.BankNotFoundException;
import com.project.bankmanag.models.Bank;
import com.project.bankmanag.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
	this.bankRepository = bankRepository;
    }

    @Override
    public List<Bank> getAll() {
	return bankRepository.findAll();
    }

    @Override
    public Bank getClientById(Long id) {
	return bankRepository.findById(id).orElseThrow( () -> new BankException(id));
    }

    @Override
    public Bank addClient(Bank bank) {
        try {
	    return bankRepository.save(bank);
	} catch (Exception e) {
         throw new BankException("Cannot save bank!", e.getCause());
	}

    }

    @Override
    public Bank updateClient(Bank bankToUpdate) {
	Bank currentBank = bankRepository.findById(bankToUpdate.getId())
			.orElseThrow(() -> new BankNotFoundException("Cannot find bank with id: " + bankToUpdate.getId()));
	return bankRepository.save(bankToUpdate);
    }

    @Override
    public void deleteClient(Long id) {

    }
}
