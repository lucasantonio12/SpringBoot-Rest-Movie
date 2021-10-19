package com.example.springAplicationUser.service;

import com.example.springAplicationUser.data.UserDate;
import com.example.springAplicationUser.model.User;
import com.example.springAplicationUser.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Component
public class UserService implements UserDetailsService {
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
    public Optional<User> findByLogin(String login){return repository.findByUsername(login);}
    public void deleteUser(Long id){
        Optional<User> user = findByID(id);
        repository.deleteById(user.get().getId());}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        if(user.isEmpty())
            throw new UsernameNotFoundException("User - "+ username +" Not Found");

        return new UserDate(user);
    }
}
