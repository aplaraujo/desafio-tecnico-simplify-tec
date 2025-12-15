package io.github.aplaraujo.controllers;

import io.github.aplaraujo.dto.TodoRequestDTO;
import io.github.aplaraujo.dto.TodoResponseDTO;
import io.github.aplaraujo.mappers.TodoMapper;
import io.github.aplaraujo.security.UserIdentity;
import io.github.aplaraujo.services.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/todos")
public class TodoController {
    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }

    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserIdentity userIdentity = (UserIdentity) auth.getPrincipal();
        return userIdentity.getId();
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TodoResponseDTO> save(@RequestBody TodoRequestDTO request) {
        Long userId = getCurrentUserId();
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.save(request));
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TodoResponseDTO> findById(@PathVariable("id") String id) {
        var todoId = Long.parseLong(id);
        Long userId = getCurrentUserId();

        return todoService.findById(todoId, userId).map(todo -> {
            TodoResponseDTO dto = todoMapper.toResponse(todo);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<TodoResponseDTO> todos() {
        Long userId = getCurrentUserId();
        return todoService.todos(userId);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TodoResponseDTO> update(@PathVariable("id") String id, @RequestBody TodoRequestDTO request) {
        var todoId = Long.parseLong(id);
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(todoService.update(todoId, request, userId));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        var todoId = Long.parseLong(id);
        Long userId = getCurrentUserId();
        todoService.delete(todoId, userId);
        return ResponseEntity.noContent().build();
    }
}
