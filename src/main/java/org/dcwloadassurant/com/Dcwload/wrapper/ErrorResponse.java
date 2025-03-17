package org.dcwloadassurant.com.Dcwload.wrapper;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ErrorResponse {

    private LocalDateTime date;
    private String message;

    private String details;

    public ErrorResponse(LocalDateTime date, String message , String details){
        this.date = date;
        this.message = message;
        this.details = details;

    }
}
