package org.hong.quotesapi.entity.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.hong.quotesapi.entity.error.BaseError;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hong
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class ErrorResponse extends BaseResponse {

    private final List<BaseError> errors = new ArrayList<>();

    public void addError(BaseError error) {
        errors.add(error);
    }

    public void addErrors(List<BaseError> errors) {
        this.errors.addAll(errors);
    }

}
