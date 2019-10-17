package com.project.bankmanag.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.bankmanag.models.Client;

@Service
public interface ClientService {
	
	public List<Client> getAll();
	
	public Optional<Client> getClient(Long id);
	
	public Client addClient(Client client);
	
	public Client updateClient(Client clientToUpdate, Long id);
	
	public void deleteClient(Long id);
}
