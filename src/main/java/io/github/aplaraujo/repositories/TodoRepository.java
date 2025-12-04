package io.github.aplaraujo.repositories;

import io.github.aplaraujo.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
