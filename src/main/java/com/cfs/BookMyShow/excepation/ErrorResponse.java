package com.cfs.BookMyShow.excepation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private Date timestamp;
    private int status;
    private String error;
    private String massage;
    private String path;
}
