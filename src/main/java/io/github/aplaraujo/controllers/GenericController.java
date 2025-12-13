package io.github.aplaraujo.controllers;
import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public interface GenericController {
    default URI generateHeaderLocation(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
