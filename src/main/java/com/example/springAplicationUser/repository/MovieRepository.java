package com.example.springAplicationUser.repository;

import com.example.springAplicationUser.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    public List<Movie> findByTitleIgnoreCase(String title);
}
