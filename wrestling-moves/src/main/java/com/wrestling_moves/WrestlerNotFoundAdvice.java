package com.wrestling_moves;

import com.wrestling_moves.exceptions.WrestlerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class WrestlerNotFoundAdvice {

    @ExceptionHandler(WrestlerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String wrestlerNotFoundHandler(WrestlerNotFoundException ex) {
        return ex.getMessage();
    }
}
