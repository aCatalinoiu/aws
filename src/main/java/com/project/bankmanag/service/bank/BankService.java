package com.project.bankmanag.service.bank;

import com.project.bankmanag.model.Bank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface BankService {

    Page<Bank> getAll(Pageable pageable);

    Bank getClientById(Long id);

    Bank addClient(Bank client);

    Bank updateClient(Bank clientToUpdate, Long id);

    void deleteClient(Long id);
}
