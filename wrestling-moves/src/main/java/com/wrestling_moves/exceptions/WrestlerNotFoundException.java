package com.wrestling_moves.exceptions;

public class WrestlerNotFoundException extends RuntimeException {

    public WrestlerNotFoundException(Long id) {
        super("Could not find wrestler " + id);
    }
}