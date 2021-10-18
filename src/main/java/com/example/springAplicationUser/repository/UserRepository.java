package com.example.springAplicationUser.repository;

import com.example.springAplicationUser.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findByNameIgnoreCase(String name);
    public Optional<User> findByUsername(String login);
}
