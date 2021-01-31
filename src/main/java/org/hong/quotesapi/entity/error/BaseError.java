package org.hong.quotesapi.entity.error;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @author hong
 */
@Data
@SuperBuilder
public class BaseError {
    String code;
    String message;
}
