package io.pivotal.rest.springpoc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@AllArgsConstructor
@Getter
@Setter
public class ExceptionResponse {

    private LocalDate timeStamp;

    private String errorMessage;

    private String details;
}
