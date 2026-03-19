package com.cfs.BookMyShow.excepation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SeatUnavilableExcepation extends RuntimeException {
    public SeatUnavilableExcepation(String massage){
        super(massage);
    }
}
