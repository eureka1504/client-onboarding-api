package com.client_onboarding.client_onboarding_demo.repository;

import com.client_onboarding.client_onboarding_demo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
