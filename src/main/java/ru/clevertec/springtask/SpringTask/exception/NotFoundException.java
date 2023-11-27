package ru.clevertec.springtask.SpringTask.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException{
    private Object errorDetails;
    public NotFoundException(String message, Object errorDetails) {
        super(message);
        this.errorDetails = errorDetails;
    }

    public NotFoundException(String message, Object errorDetails, Throwable cause) {
        super(message, cause);
        this.errorDetails = errorDetails;
    }
}
