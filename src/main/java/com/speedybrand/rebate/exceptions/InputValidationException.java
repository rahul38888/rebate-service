package com.speedybrand.rebate.exceptions;

public class InputValidationException extends RebateServiceException{

    public InputValidationException(final Object entity, final Throwable cause) {
        super(HttpErrorStatus.INVALID_INPUT,
                HttpErrorStatus.INVALID_INPUT.getMessageFormat().formatted(entity), cause);
    }
}
