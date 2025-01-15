package com.speedybrand.rebate.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum HttpErrorStatus {

    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value() + "_001",
            "Entity not found in the repository, %s"),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value() + "_001",
            "Invalid input provided, %s"),
    UPDATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value() + "_001",
            "Database update failure"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value() + "_002",
            "Internal server exception"),
    INVALID_DB_STATE_ERROR(HttpStatus.UNPROCESSABLE_ENTITY, HttpStatus.UNPROCESSABLE_ENTITY.value() + "_001",
            "Failure due to invalid state of DB");

    private final HttpStatus status;
    private final String subStatus;
    private final String messageFormat;

    HttpErrorStatus(final HttpStatus status, final String subStatus, final String messageFormat) {
        this.status = status;
        this.subStatus = subStatus;
        this.messageFormat = messageFormat;
    }
}
