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

    public TodoResponseDTO save(TodoRequestDTO request, Long userId) {
        Todo todo = todoMapper.toEntity(request);
        todo.setUserId(userId);
        todoRepository.save(todo);
        return todoMapper.toResponse(todo);
    }

    public Optional<Todo> findById(Long id, Long userId) {
        if (!todoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Todo not found!");
        }
        return todoRepository.findByIdAndUserId(id, userId);
    }

    public List<TodoResponseDTO> todos(Long userId) {
        return todoRepository.findByUserId(userId).stream().map(todoMapper::toResponse).toList();
    }

    public TodoResponseDTO update(Long id, TodoRequestDTO request, Long userId) {
        Todo todo = todoRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new ResourceNotFoundException("Todo not found!"));
        todo.setName(request.name());
        todo.setDescription(request.description());
        todo.setDone(request.done());
        todo.setPriority(request.priority());

        return todoMapper.toResponse(todoRepository.save(todo));
    }

    public void delete(Long id, Long userId) {
        Todo todo = todoRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Todo not found or access denied"));

        todoRepository.delete(todo);
    }
}
