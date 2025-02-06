package com.primeit.agilejava.domain.exception;

/**
 * Excepción lanzada cuando se intenta crear un usuario con un username duplicado.
 */
public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String message) {
        super(message);
    }
}
