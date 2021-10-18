package com.example.springAplicationUser.service;

import com.example.springAplicationUser.model.User;
import com.example.springAplicationUser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    public User saveUser(User user){
        return repository.saveAndFlush(user);
    }
    public Optional<User> findByID(Long id){ return repository.findById(id);}
    public List<User> findByNameIgnoreCase(String name){
        return  repository.findByNameIgnoreCase(name);
    }
    public List<User> getAll(){
        return repository.findAll();
    }

}
