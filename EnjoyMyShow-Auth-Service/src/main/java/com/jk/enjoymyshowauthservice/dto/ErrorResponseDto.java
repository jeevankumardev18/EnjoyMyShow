package com.jk.enjoymyshowauthservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponseDto
{
    private  String message;
    private  int status;
    private  LocalDateTime timeStamp;

    public ErrorResponseDto(String message, int status) {
        this.message = message;
        this.status = status;
        this.timeStamp = LocalDateTime.now();
    }

    public ErrorResponseDto(String message, int status, LocalDateTime timeStamp) {
        this.message = message;
        this.status = status;
        this.timeStamp = timeStamp;
    }

}
