package io.github.aplaraujo.repositories;

import io.github.aplaraujo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByNameIn(List<String> names);
    String findByName(String name);
}
