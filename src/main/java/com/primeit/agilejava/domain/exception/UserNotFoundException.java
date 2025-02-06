package com.primeit.agilejava.domain.exception;
/**
 * Excepción para cuando un usuario no se encuentra.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
