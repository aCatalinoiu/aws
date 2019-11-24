package com.project.bankmanag.controller;

import com.project.bankmanag.dto.ClientDTO;
import com.project.bankmanag.mapper.ClientMapper;
import com.project.bankmanag.model.Client;
import com.project.bankmanag.service.client.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @ApiOperation("Find all clients")
    @ApiResponses(value = @ApiResponse(code = 200, message = "List of clients received successfully"))
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
        Page<ClientDTO> clientDTOPage = clientService.getAll(pageable).map(ClientMapper::toClientDTO);
        return ResponseEntity.status(HttpStatus.OK).body(clientDTOPage);
    }

    @PostMapping
    @ApiOperation("Add new client")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Client created successfully"),
            @ApiResponse(code = 409, message = "A client already exists with same CNP"),
            @ApiResponse(code = 400, message = "Field validation failed")})
    public ResponseEntity<ClientDTO> addNewClient(@RequestBody @Valid Client newClient) {
        ClientDTO expectedClientDTO = ClientMapper.toClientDTO(clientService.addClient(newClient));
        return ResponseEntity.status(HttpStatus.OK).body(expectedClientDTO);
    }

    @GetMapping("/{clientId}")
    @ApiOperation("Find client by Id")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Client found"),
            @ApiResponse(code = 404, message = "Could not find client")})
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long clientId) {
        Client expectedClient = clientService.getClientById(clientId);
        return expectedClient != null ?
                ResponseEntity.status(HttpStatus.OK).body(ClientMapper.toClientDTO(expectedClient)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{clientId}")
    @ApiOperation("Update client")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Client updated successfully"),
            @ApiResponse(code = 404, message = "Could not find client to update"),
            @ApiResponse(code = 400, message = "Field validation failed")})
    public ResponseEntity<ClientDTO> updateClient(@RequestBody @Valid Client newClient, @PathVariable Long clientId) {
        Client updatedClient = clientService.updateClient(newClient, clientId);
        return updatedClient != null ?
                ResponseEntity.status(HttpStatus.OK).body(ClientMapper.toClientDTO(updatedClient)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("{clientId}")
    @ApiOperation("Delete client by Id")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Client deleted successfully"),
            @ApiResponse(code = 404, message = "Could not find client to delete")})
    public ResponseEntity<Void> removeClient(@PathVariable Long clientId) {
        clientService.deleteClient(clientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
