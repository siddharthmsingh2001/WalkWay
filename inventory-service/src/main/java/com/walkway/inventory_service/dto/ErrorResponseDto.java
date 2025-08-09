package com.walkway.inventory_service.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import com.walkway.inventory_service.exception.ResponseStatus;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class ErrorResponseDto implements Serializable {

    private String apiPath;
    private int statusCode;
    private HttpStatus errorStatus;
    private String errorCode;
    private LocalDateTime errorTime;
    private String causeMsg;

    public ErrorResponseDto(String apiPath, ResponseStatus responseStatus, String causeMsg){
        this.apiPath = apiPath;
        this.statusCode = responseStatus.getStatusCode();
        this.errorStatus = responseStatus.getStatusMsg();
        this.errorCode = responseStatus.name();
        this.errorTime = LocalDateTime.now();
        this.causeMsg = causeMsg;
    }
}
