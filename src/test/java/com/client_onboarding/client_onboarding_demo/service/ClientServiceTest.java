package com.client_onboarding.client_onboarding_demo.service;

import com.client_onboarding.client_onboarding_demo.model.*;
import com.client_onboarding.client_onboarding_demo.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepo;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void serviceShouldBeCreated()
    {
        assertNotNull(clientService);
    }

    @Test
    public void getClient_valid_shouldReturnClientDTO()
    {
        Client client = new Client();
        client.setId(1L);
        client.setName("John Doe");
        client.setEmail("john.doe@email.com");
        client.setStatus(OnboardingStatus.NEW);

        when(clientRepo.findById(1L))
                .thenReturn(Optional.of(client));

        ClientDTO result = clientService.getClient(1L);

        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@email.com", result.getEmail());
        assertEquals(OnboardingStatus.NEW, result.getStatus());
    }

    @Test
    public void getClient_missingId_shouldThrowException()
    {
        when(clientRepo.findById(99L))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> clientService.getClient(99L));

        assertEquals("Could not find client by id: 99", ex.getMessage());
    }

    @Test
    public void addClient_shouldSaveClientAndReturnClientDTO()
    {
        CreateClientRequest request = new CreateClientRequest();
        request.setName("John Doe");
        request.setEmail("john.doe@email.com");

        Client savedClient = new Client();
        savedClient.setId(1L);
        savedClient.setName("John Doe");
        savedClient.setEmail("john.doe@email.com");
        savedClient.setStatus(OnboardingStatus.NEW);

        when(clientRepo.save(any(Client.class)))
                .thenReturn(savedClient);

        ClientDTO result = clientService.addClient(request);

        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@email.com", result.getEmail());
        assertEquals(OnboardingStatus.NEW, result.getStatus());

        verify(clientRepo).save(any(Client.class));
    }

    @Test
    public void addClient_shouldBuildClientAndSave()
    {
        CreateClientRequest request = new CreateClientRequest();
        request.setName("John Doe");
        request.setEmail("john.doe@email.com");

        Client savedClient = new Client();
        savedClient.setId(1L);
        savedClient.setName("John Doe");
        savedClient.setEmail("john.doe@email.com");
        savedClient.setStatus(OnboardingStatus.NEW);

        when(clientRepo.save(any(Client.class)))
                .thenReturn(savedClient);

        clientService.addClient(request);

        ArgumentCaptor<Client> captor = ArgumentCaptor.forClass(Client.class);

        verify(clientRepo).save(captor.capture());

        Client capturedClient = captor.getValue();

        assertEquals("John Doe", capturedClient.getName());
        assertEquals("john.doe@email.com", capturedClient.getEmail());
        assertEquals(OnboardingStatus.NEW, capturedClient.getStatus());
    }

    @Test
    public void updateStatus_shouldUpdateClientStatusAndSave()
    {
        UpdateStatusRequest request = new UpdateStatusRequest();
        request.setStatus(OnboardingStatus.APPROVED);

        Client client = new Client();
        client.setId(1L);
        client.setName("John Doe");
        client.setEmail("john.doe@email.com");
        client.setStatus(OnboardingStatus.NEW);

        when(clientRepo.findById(1L))
                .thenReturn(Optional.of(client));

        when(clientRepo.save(any(Client.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ArgumentCaptor<Client> captor = ArgumentCaptor.forClass(Client.class);
        ClientDTO result = clientService.updateStatus(1L, request);

        verify(clientRepo).findById(1L);
        verify(clientRepo).save(captor.capture());

        Client capturedClient = captor.getValue();

        assertEquals(OnboardingStatus.APPROVED, capturedClient.getStatus());
        assertEquals(OnboardingStatus.APPROVED, result.getStatus());
    }
}
