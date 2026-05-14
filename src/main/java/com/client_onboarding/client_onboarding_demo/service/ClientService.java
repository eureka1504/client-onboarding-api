package com.client_onboarding.client_onboarding_demo.service;

import com.client_onboarding.client_onboarding_demo.model.*;
import com.client_onboarding.client_onboarding_demo.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository repo;

    public ClientService(ClientRepository repo)
    {
        this.repo = repo;
    }

    public ClientDTO getClient(Long id)
    {
        Client client = repo.findById(id).orElseThrow(() ->  new RuntimeException("Could not find client by id: " + id));

        return toDTO(client);
    }

    public List<ClientDTO> getClients()
    {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    public ClientDTO addClient(CreateClientRequest clientRequest)
    {
        Client client = new Client();
        client.setName(clientRequest.getName());
        client.setEmail(clientRequest.getEmail());
        client.setStatus(OnboardingStatus.NEW);

        return toDTO(repo.save(client));
    }

    public ClientDTO updateStatus(Long id, UpdateStatusRequest statusRequest)
    {
        Client client = repo.findById(id).orElseThrow(() ->  new RuntimeException("Could not find client by id: " + id));

        client.setStatus(statusRequest.getStatus());

        return toDTO(repo.save(client));
    }

    private ClientDTO toDTO(Client client)
    {
        ClientDTO dTO = new ClientDTO();
        dTO.setId(client.getId());
        dTO.setName(client.getName());
        dTO.setEmail(client.getEmail());
        dTO.setStatus(client.getStatus());
        return dTO;
    }
}
