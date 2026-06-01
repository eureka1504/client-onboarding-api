package com.client_onboarding.client_onboarding_demo.controller;

import com.client_onboarding.client_onboarding_demo.model.ClientDTO;
import com.client_onboarding.client_onboarding_demo.model.CreateClientRequest;
import com.client_onboarding.client_onboarding_demo.model.UpdateStatusRequest;
import com.client_onboarding.client_onboarding_demo.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService)
    {
        this.clientService = clientService;
    }

    @GetMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id)
    {
        return clientService.getClient(id);
    }

    @GetMapping("/clients")
    public List<ClientDTO> getClients()
    {
        return clientService.getClients();
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO addClient(@Valid @RequestBody CreateClientRequest clientRequest)
    {
        return clientService.addClient(clientRequest);
    }

    @PutMapping("/clients/{id}/status")
    public ClientDTO updateStatus(@PathVariable Long id, @Valid @RequestBody UpdateStatusRequest statusRequest)
    {
        return clientService.updateStatus(id, statusRequest);
    }
}
