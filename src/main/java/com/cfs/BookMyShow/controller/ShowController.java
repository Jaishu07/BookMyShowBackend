package com.cfs.BookMyShow.controller;

import com.cfs.BookMyShow.dto.ShowDto;
import com.cfs.BookMyShow.service.ShowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @PostMapping
    public ShowDto createShow(@RequestBody ShowDto dto) {
        return showService.createShow(dto);
    }

    @GetMapping("/movie/{movieId}")
    public List<ShowDto> getByMovie(@PathVariable Long movieId) {
        return showService.getShowsByMovie(movieId);
    }
}