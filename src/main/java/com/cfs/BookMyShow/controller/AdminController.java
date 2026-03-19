package com.cfs.BookMyShow.controller;

import com.cfs.BookMyShow.dto.MovieDto;
import com.cfs.BookMyShow.service.MovieService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final MovieService movieService;

    public AdminController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/movies")
    public MovieDto createMovie(@RequestBody MovieDto dto) {
        return movieService.createMovie(dto);
    }

    @DeleteMapping("/movies/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return "Deleted";
    }
}