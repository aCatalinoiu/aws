package com.project.bankmanag.service.client;

import com.project.bankmanag.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    Page<Client> getAll(Pageable pageable);

    Client getClientById(Long id);

    Client addClient(Client client);

    Client updateClient(Client clientToUpdate, Long id);

    void deleteClient(Long id);
}
