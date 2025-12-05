package io.github.aplaraujo.services;

import io.github.aplaraujo.dto.TodoRequestDTO;
import io.github.aplaraujo.dto.TodoResponseDTO;
import io.github.aplaraujo.entities.Todo;
import io.github.aplaraujo.exceptions.ResourceNotFoundException;
import io.github.aplaraujo.mappers.TodoMapper;
import io.github.aplaraujo.repositories.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    public TodoResponseDTO save(TodoRequestDTO request) {
        Todo todo = todoMapper.toEntity(request);
        todoRepository.save(todo);
        return todoMapper.toResponse(todo);
    }

    public Optional<Todo> findById(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Todo not found!");
        }
        return todoRepository.findById(id);
    }

    public List<TodoResponseDTO> todos() {
        return todoRepository.findAll().stream().map(todoMapper::toResponse).toList();
    }
}
