package com.client_onboarding.client_onboarding_demo.controller;

import com.client_onboarding.client_onboarding_demo.model.ClientDTO;
import com.client_onboarding.client_onboarding_demo.model.OnboardingStatus;
import com.client_onboarding.client_onboarding_demo.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClientService clientService;

    @Test
    public void getClient_shouldReturnClientDTO() throws Exception
    {
        ClientDTO dto = new ClientDTO();
        dto.setId(1L);
        dto.setName("John Doe");
        dto.setEmail("john.doe@email.com");
        dto.setStatus(OnboardingStatus.NEW);

        when(clientService.getClient(1L))
                .thenReturn(dto);

        mockMvc.perform(get("/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@email.com"))
                .andExpect(jsonPath("$.status").value(OnboardingStatus.NEW.name()));
    }
}