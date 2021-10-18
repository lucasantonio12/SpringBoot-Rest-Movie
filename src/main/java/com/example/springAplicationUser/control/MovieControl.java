package com.example.springAplicationUser.control;

import com.example.springAplicationUser.model.Movie;
import com.example.springAplicationUser.service.MovieService;
import com.example.springAplicationUser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie")
public class MovieControl {
    private MovieService service;


    @Autowired
    public void setService(MovieService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<Movie> save(@RequestBody Movie movie) {
        this.service.saveMovie(movie);
        return ResponseEntity.created(URI.create("/movies/" + movie.getId())).build();
    }

    @GetMapping("/list")
    public List<Movie> listAll() {
        return this.service.getAll();
    }

    @GetMapping("/title/{title}")
    public List<Movie> listTitle(@PathVariable("title") String title) {
        return this.service.findByTitleIgnoreCase(title);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> listMovieId(@PathVariable("id") Long id) {
        Optional<Movie> movie = service.findById(id);
        if (movie.isPresent())
            return ResponseEntity.ok().body(movie.get());

        return ResponseEntity.notFound().build();
    }

}
