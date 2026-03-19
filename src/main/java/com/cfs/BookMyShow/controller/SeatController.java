package com.cfs.BookMyShow.controller;

import com.cfs.BookMyShow.model.ShowSeat;
import com.cfs.BookMyShow.repository.ShowSeatRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final ShowSeatRepository showSeatRepository;

    public SeatController(ShowSeatRepository showSeatRepository) {
        this.showSeatRepository = showSeatRepository;
    }

    @GetMapping("/show/{showId}")
    public List<ShowSeat> getSeats(@PathVariable Long showId) {
        return showSeatRepository.findByShowId(showId);
    }
}