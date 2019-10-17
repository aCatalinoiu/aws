package com.project.bankmanag.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bankmanag.models.Client;
import com.project.bankmanag.services.ClientService;
import com.project.bankmanag.exceptions.ClientNotFoundException;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clients")
public class ClientController {
	private ClientService clientService;
	
	@Autowired
	public void setClientService(ClientService clientService){
		this.clientService = clientService;
	}
	
	@GetMapping
	@ApiOperation("Find all clients")
	List<Client> findAll() {
        return clientService.getAll();
	 } 
	
	@PostMapping
	@ApiOperation("Add new client")
	// id
	Long newClient(@RequestBody @Valid Client newClient) {
		 return clientService.addClient(newClient).getClientId();
	}
	
	@GetMapping("{id}")
	@ApiOperation("Find client by Id")
	Client getClient(@PathVariable Long id){
		return clientService.getClient(id).orElseThrow(() -> new ClientNotFoundException(id));
	}
	
	@PutMapping("{id}")
	@ApiOperation("Update client")
	Client updateClient(@RequestBody @Valid Client newClient, @PathVariable Long id){
		return clientService.updateClient(newClient, id);
	}
	
	@DeleteMapping("{id}")
	@ApiOperation("Delete client by Id")
	ResponseEntity<Object> removeClient(@PathVariable Long id){
		if(!getClient(id).equals(null)){
			clientService.deleteClient(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else
			return new ResponseEntity<>(new ClientNotFoundException(id), HttpStatus.BAD_REQUEST);
	}
}
