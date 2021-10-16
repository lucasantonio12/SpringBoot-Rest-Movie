package com.example.springAplicationUser.control;

import com.example.springAplicationUser.model.Movie;
import com.example.springAplicationUser.service.MovieService;
import com.example.springAplicationUser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieControl {
    private MovieService service;
    private UserService userService;

    @Autowired
    public void setService(MovieService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public Movie save(@RequestBody Movie movie){
        return service.saveMovie(movie);
    }

    @GetMapping("/list")
    public List<Movie> listAll(){
        return service.getAll();
    }

    @GetMapping("/title/{title}")
    public  List<Movie> listTitle(@PathVariable("Tittle") String title){
        return service.findByTitleIgnoreCase(title);
    }

    @GetMapping("/movie/{id}")
    public  ResponseEntity<Movie> listMovieId(@PathVariable("id") Long id){
        if(service.getMovieId(id) == null)
           return ResponseEntity.notFound().build();
       else
           return  ResponseEntity.ok().body(service.getMovieId(id));
    }
}
