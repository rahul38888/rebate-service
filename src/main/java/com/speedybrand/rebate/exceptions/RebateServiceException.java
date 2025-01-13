package com.speedybrand.rebate.exceptions;

import lombok.Getter;

@Getter
public class RebateServiceException extends RuntimeException {

    private final HttpErrorStatus status;

    public RebateServiceException(final HttpErrorStatus status, final String message, final Throwable cause) {
        super(message, cause);
        this.status = status;
    }
}
