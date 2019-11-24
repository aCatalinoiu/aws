package com.project.bankmanag.service.client;

import com.project.bankmanag.exception.client.ClientNotFoundException;
import com.project.bankmanag.model.Client;
import com.project.bankmanag.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Page<Client> getAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client clientToUpdate, Long id) {
        clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        clientToUpdate.setClientId(id);
        return clientRepository.save(clientToUpdate);
    }

    @Override
    public void deleteClient(Long id) {
		clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
		clientRepository.deleteById(id);
	}

}
