package com.project.bankmanag.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<Client> getAll(){
		List<Client> clients = new ArrayList<>();
		try {
			LOG.info("Getting all clients...");
			clients = clientRepository.findAll();
		} catch (Exception e){
			LOG.error("An error ocurred during getting all clients:" + e.getMessage());
		}
		return clients;
	}
	
	@Override
	public Optional<Client> getClient(Long id){
		Optional<Client> clientFound = null;
		try {
			LOG.info("Getting the client with the given id:" + id);
			clientFound = clientRepository.findById(id);
		} catch(Exception e){
			LOG.error("An error ocurred during getting client:" + e.getMessage());
		}
		return clientFound;
	}
	
	@Override
	public Client addClient(Client client){
		Client newClient = new Client();
		try {
			LOG.info("Adding new client...");
			newClient = clientRepository.save(client);
		} catch(Exception e){
			LOG.error("An error ocurred during adding client:" + e.getMessage());
		}
		return newClient;
	}
	
	@Override
	public Client updateClient(Client clientToUpdate, Long id){
		// exception nullpointer
		Client foundClient = clientRepository.findById(id).get();
		try {
			LOG.info("Updating client...");
			// add Client setAll
			foundClient.populate(clientToUpdate.getFirstName(), clientToUpdate.getLastName(), clientToUpdate.getCnp(), clientToUpdate.getPoB(),
					clientToUpdate.getDoB());
			return clientRepository.save(foundClient);
		} catch(Exception e){
			LOG.error("An error ocurred during updating client:" + e.getMessage());
		}
		return clientToUpdate;
	}
	
	@Override
	public void deleteClient(Long id){
		try {
			LOG.info("Deleting client...");
			clientRepository.delete(clientRepository.findById(id).get());
		} catch(Exception e){
			LOG.error("An error ocurred during deleting client:" + e.getMessage());
		}
	}
}
