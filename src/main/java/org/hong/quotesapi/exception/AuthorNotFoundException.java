package org.hong.quotesapi.exception;


/**
 * It also extends {@link RuntimeException}
 *
 * @author hong
 */
public class AuthorNotFoundException extends NotFoundException {

    private final static String AUTHOR_NOT_FOUND = "Author not found!";

    public AuthorNotFoundException() {
        this(AUTHOR_NOT_FOUND);
    }

    public AuthorNotFoundException(String message) {
        super(message);
    }
}
