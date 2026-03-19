package com.cfs.BookMyShow.controller;

import com.cfs.BookMyShow.dto.TheaterDto;
import com.cfs.BookMyShow.service.TheaterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {

    private final TheaterService theaterService;

    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @PostMapping
    public TheaterDto create(@RequestBody TheaterDto dto) {
        return theaterService.createTheater(dto);
    }

    @GetMapping
    public List<TheaterDto> getAll() {
        return theaterService.getAllTheaters();
    }

    @GetMapping("/city/{city}")
    public List<TheaterDto> getByCity(@PathVariable String city) {
        return theaterService.getAllTheaterByCity(city);
    }
}