package com.speedybrand.rebate.exceptions;

public class UpdateFailureException extends RebateServiceException{

    public UpdateFailureException(final Throwable cause) {
        super(HttpErrorStatus.UPDATE_ERROR, HttpErrorStatus.UPDATE_ERROR.getMessageFormat(), cause);
    }
}
