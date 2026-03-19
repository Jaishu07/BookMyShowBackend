package com.cfs.BookMyShow.repository;

import com.cfs.BookMyShow.model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    // Show ke saare seats
    List<ShowSeat> findByShowId(Long showId);

    // Available seats
    List<ShowSeat> findByShowIdAndStatus(Long showId, String status);
}