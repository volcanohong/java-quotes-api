package org.hong.quotesapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is a new option to handle response in spring 5
 *
 * This extends Exception
 * Example: https://www.baeldung.com/spring-response-status-exception
 *
 * Not in use currently, could give it a try!
 *
 * @author hong
 */
@ResponseStatus(code = HttpStatus.OK, reason = "Author Not Found ...")
public class NewAuthorNotFoundException extends Exception {
}
