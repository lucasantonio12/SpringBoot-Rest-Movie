package com.example.springAplicationUser.control;


import com.example.springAplicationUser.model.User;
import com.example.springAplicationUser.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControl {
    private UserService service;

    @Autowired
    public void setRepository(UserService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user){
        return this.service.saveUser(user);
    }

    @GetMapping("/listUser")
    private List<User> listAll(){
        return this.service.getAll();
    }

    @GetMapping("/findByName/{name}")
    public List<User> listName(@PathVariable("name") String name) {
        return this.service.findByNameIgnoreCase(name);
    }

    @GetMapping("/findById/{id}")
    public User listId(@PathVariable("id") Long id) {
        return this.service.getUserId(id);
    }

}
