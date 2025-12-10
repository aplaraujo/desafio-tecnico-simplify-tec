package io.github.aplaraujo.services;

import io.github.aplaraujo.entities.Client;
import io.github.aplaraujo.repositories.ClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Client save(Client client) {
        var encryptedPassword = passwordEncoder.encode(client.getClientSecret());
        client.setClientSecret(encryptedPassword);
        return clientRepository.save(client);
    }

    public Client findByClientId(String clientId) {
        return clientRepository.findByClientId(clientId);
    }
}
