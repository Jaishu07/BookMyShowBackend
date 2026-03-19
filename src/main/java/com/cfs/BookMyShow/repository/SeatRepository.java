package com.cfs.BookMyShow.repository;



import com.cfs.BookMyShow.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    // Screen ke saare seats
    List<Seat> findByScreenId(Long screenId);
}