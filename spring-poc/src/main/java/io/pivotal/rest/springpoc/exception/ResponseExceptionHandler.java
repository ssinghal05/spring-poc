package io.pivotal.rest.springpoc.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@RestController
@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundExceptions(Exception exception, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDate.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleExceptions(Exception exception, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDate.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDate.now(), "Validation failed", ex.getBindingResult().getAllErrors().toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

    }
}
