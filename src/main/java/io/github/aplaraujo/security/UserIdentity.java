package io.github.aplaraujo.security;

import io.github.aplaraujo.entities.Role;

import java.util.Set;

public class UserIdentity {
    private Long id;
    private String login;
    private String name;
    private Set<Role> roles;

    public UserIdentity() {
    }

    public UserIdentity(Long id, String login, String name) {
        this.id = id;
        this.login = login;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
