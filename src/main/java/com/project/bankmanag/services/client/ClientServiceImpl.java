package com.project.bankmanag.services.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bankmanag.exceptions.client.ClientExistException;
import com.project.bankmanag.exceptions.client.ClientNotFoundException;
import com.project.bankmanag.models.Client;
import com.project.bankmanag.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {
    private Logger LOG = LoggerFactory.getLogger(ClientService.class);
    private ClientRepository clientRepository;

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
	this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAll() {
	return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
	Optional<Client> client = clientRepository.findById(id);
	if (client.isPresent())
	    return client.get();
	else
	    throw new ClientNotFoundException(id);
    }

    @Override
    public Client addClient(Client client) {
	Client newClient = new Client();
	try {
	    LOG.info("Adding new client...");
	    newClient = clientRepository.save(client);
	} catch (Exception e) {
	    LOG.error("An error ocurred during adding client:" + e.getMessage());
	    throw new ClientExistException(client.getCnp());
	}
	return newClient;
    }

    @Override
    public Client updateClient(Client clientToUpdate) {
	return clientRepository.save(clientRepository.findById(clientToUpdate.getClientId())
			.orElseThrow(() -> new ClientNotFoundException(clientToUpdate.getClientId())));
    }

    @Override
    public void deleteClient(Long id) {
	try {
	    LOG.info("Deleting client...");
	    clientRepository.delete(clientRepository.findById(id).get());
	} catch (Exception e) {
	    LOG.error("An error ocurred during deleting client:" + e.getMessage());
	}
    }

}
