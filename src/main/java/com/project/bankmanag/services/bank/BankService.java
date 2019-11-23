package com.project.bankmanag.services.bank;

import com.project.bankmanag.models.Bank;
import com.project.bankmanag.models.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankService {

    List<Bank> getAll();

    Bank getClientById(Long id);

    Bank addClient(Bank client);

    Bank updateClient(Bank clientToUpdate);

    void deleteClient(Long id);
}
