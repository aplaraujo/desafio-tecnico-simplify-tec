package io.github.aplaraujo.repositories;

import io.github.aplaraujo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
