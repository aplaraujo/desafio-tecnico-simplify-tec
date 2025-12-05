package io.github.aplaraujo.controllers;

import io.github.aplaraujo.dto.TodoRequestDTO;
import io.github.aplaraujo.dto.TodoResponseDTO;
import io.github.aplaraujo.mappers.TodoMapper;
import io.github.aplaraujo.services.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<TodoResponseDTO> save(@RequestBody TodoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.save(request));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TodoResponseDTO> findById(@PathVariable("id") String id) {
        var todoId = Long.parseLong(id);

        return todoService.findById(todoId).map(todo -> {
            TodoResponseDTO dto = todoMapper.toResponse(todo);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<TodoResponseDTO> todos() {
        return todoService.todos();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TodoResponseDTO> update(@PathVariable("id") String id, @RequestBody TodoRequestDTO request) {
        var todoId = Long.parseLong(id);
        return ResponseEntity.ok(todoService.update(todoId, request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        var todoId = Long.parseLong(id);
        todoService.delete(todoId);
        return ResponseEntity.noContent().build();
    }
}
