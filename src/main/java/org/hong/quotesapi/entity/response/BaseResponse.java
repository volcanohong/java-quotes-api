package org.hong.quotesapi.entity.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

/**
 *
 * @author hong
 */
@Data
@SuperBuilder
public class BaseResponse {
    HttpStatus status;
    Object body;
}
