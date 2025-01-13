package com.speedybrand.rebate.exceptions;

import java.util.Map;

public class EntityNotFoundException extends RebateServiceException{

    public EntityNotFoundException(final Map<String, Object> id, final Throwable cause) {
        super(HttpErrorStatus.ENTITY_NOT_FOUND,
                HttpErrorStatus.ENTITY_NOT_FOUND.getMessageFormat().formatted(id), cause);
    }
}
