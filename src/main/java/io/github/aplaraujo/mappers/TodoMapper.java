package io.github.aplaraujo.mappers;

import io.github.aplaraujo.dto.TodoRequestDTO;
import io.github.aplaraujo.dto.TodoResponseDTO;
import io.github.aplaraujo.dto.UserDTO;
import io.github.aplaraujo.entities.Todo;
import io.github.aplaraujo.entities.User;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {

    public Todo toEntity(TodoRequestDTO request) {
        Todo todo = new Todo();
        todo.setName(request.name());
        todo.setDescription(request.description());
        todo.setDone(request.done());
        todo.setPriority(request.priority());

        User user = new User();
        user.setName(user.getName());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        todo.setUser(user);

        return todo;
    }

    public TodoResponseDTO toResponse(Todo todo) {
        User user = new User();
        UserDTO dto = new UserDTO(user.getName(), user.getEmail(), user.getPassword());
        return new TodoResponseDTO(todo.getId(), todo.getName(), todo.getDescription(), todo.getDone(), todo.getPriority(), user);
    }
}
