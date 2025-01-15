package com.speedybrand.rebate.exceptions;

import java.util.Map;

public class InvalidDatabaseStateException extends RebateServiceException{

    public InvalidDatabaseStateException(final Throwable cause) {
        super(HttpErrorStatus.INVALID_DB_STATE_ERROR,
                HttpErrorStatus.INVALID_DB_STATE_ERROR.getMessageFormat(), cause);
    }
}
