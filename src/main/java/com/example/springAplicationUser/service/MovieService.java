package com.example.springAplicationUser.service;

import com.example.springAplicationUser.model.Movie;
import com.example.springAplicationUser.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private MovieRepository repository;

    @Autowired
    public void setRepository(MovieRepository repository) {
        this.repository = repository;
    }

    public Movie saveMovie(Movie movie){
        return repository.saveAndFlush(movie);
    }
    public Optional<Movie> findById(Long id){
        return repository.findById(id);
    }
    public List<Movie> findByTitleIgnoreCase(String name){
        return  repository.findByTitleIgnoreCase(name);
    }
    public List<Movie> getAll(){
        return repository.findAll();
    }

}
