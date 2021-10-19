package com.example.springAplicationUser.control;


import com.example.springAplicationUser.model.User;
import com.example.springAplicationUser.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserControl {
    private UserService service;
    private PasswordEncoder encoder;

    @Autowired
    public void setRepository(UserService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return ResponseEntity.ok(this.service.saveUser(user));

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

    @GetMapping("/validPassword")
    public ResponseEntity<Boolean> validPassword(@RequestParam String login, @RequestParam String password) {

        Optional<User> optionalUser = service.findByLogin(login);
        if (optionalUser.isEmpty())
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);

        boolean valid = false;
        User user = optionalUser.get();
        valid = encoder.matches(password, user.getPassword());
        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(valid);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<User> user = this.service.findByID(id);
        if (user.isPresent()) {
            service.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
        return ResponseEntity.notFound().build();
    }



}
