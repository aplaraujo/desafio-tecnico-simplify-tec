package io.github.aplaraujo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {

    @GetMapping("/public")
    public ResponseEntity<String> publicRoute() {
        return ResponseEntity.ok("Você está na rota pública!");
    }

    @GetMapping("/private")
    public ResponseEntity<String> privateRoute(Authentication authentication) {
        return ResponseEntity.ok("Você está na rota privada! Usuário conectado: " + authentication.getName());
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> userRoute() {
        return ResponseEntity.ok("Você está na rota do usuário!");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')") // Define uma autorização para um determinado perfil
    public ResponseEntity<String> adminRoute() {
        return ResponseEntity.ok("Você está na rota do administrador!");
    }
}
