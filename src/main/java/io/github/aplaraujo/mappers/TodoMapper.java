package io.github.aplaraujo.mappers;

import io.github.aplaraujo.dto.TodoRequestDTO;
import io.github.aplaraujo.dto.TodoResponseDTO;
import io.github.aplaraujo.entities.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {

    public Todo toEntity(TodoRequestDTO request) {
        Todo todo = new Todo();
        todo.setName(request.name());
        todo.setDescription(request.description());
        todo.setDone(request.done());
        todo.setPriority(request.priority());

        return todo;
    }

    public TodoResponseDTO toResponse(Todo todo) {
        return new TodoResponseDTO(todo.getId(), todo.getName(), todo.getDescription(), todo.getDone(), todo.getPriority());
    }
}
