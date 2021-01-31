package org.hong.quotesapi.exception;


/**
 * @author hong
 */
public class NotFoundException extends RuntimeException {

    private final static String NOT_FOUND = "Not found!";

    public NotFoundException() {
        this(NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(message);
    }
}
