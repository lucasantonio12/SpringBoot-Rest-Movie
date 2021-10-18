package com.example.springAplicationUser.control;


import com.example.springAplicationUser.model.User;
import com.example.springAplicationUser.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserControl {
    private UserService service;

    @Autowired
    public void setRepository(UserService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        this.service.saveUser(user);
        return ResponseEntity.created(URI.create("/users/" + user.getId())).build();
    }

    @GetMapping("/listUser")
    private List<User> listAll() {
        return this.service.getAll();
    }

    @GetMapping("/findByName/{name}")
    public List<User> listName(@PathVariable("name") String name) {
        return this.service.findByNameIgnoreCase(name);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<User> listId(@PathVariable("id") Long id) {
        Optional<User> user = this.service.findByID(id);

        if (user.isPresent())
            return ResponseEntity.ok().body(user.get());

        return ResponseEntity.notFound().build();
    }

}
