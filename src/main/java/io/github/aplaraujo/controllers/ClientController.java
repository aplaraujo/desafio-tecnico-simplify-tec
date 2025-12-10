package io.github.aplaraujo.controllers;

import io.github.aplaraujo.entities.Client;
import io.github.aplaraujo.services.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Client client) {
        clientService.save(client);
    }
}
