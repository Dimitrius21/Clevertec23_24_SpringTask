package ru.clevertec.springtask.SpringTask.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.clevertec.springtask.SpringTask.domain.ErrorIfo;

import java.time.LocalDateTime;
import java.util.stream.Collectors;


@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundEx(NotFoundException ex, WebRequest request) {
        ErrorIfo error = new ErrorIfo(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex.getErrorDetails().toString());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String errorText = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage() + ", ")
                .collect(Collectors.joining(","));
        ErrorIfo error = new ErrorIfo(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), errorText, "");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
