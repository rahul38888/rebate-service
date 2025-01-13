package com.speedybrand.rebate.models.responses.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.speedybrand.rebate.exceptions.HttpErrorStatus;
import com.speedybrand.rebate.exceptions.RebateServiceException;
import com.speedybrand.rebate.models.responses.UniversalResponse;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseFactory {

    private ErrorResponseFactory() {}

    public static <T> ResponseEntity<UniversalResponse<T>> from(final RebateServiceException exception) {
        return createErrorResponseEntity(exception.getStatus(), exception.getMessage());
    }


    public static <T> ResponseEntity<UniversalResponse<T>> fromException(final Exception exception) {
        return createErrorResponseEntity(HttpErrorStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    private static <T> ResponseEntity<UniversalResponse<T>> createErrorResponseEntity(final HttpErrorStatus status,
                                                                                      final String message) {
        final ErrorResponse error = ErrorResponse.builder()
                .subStatus(status.getSubStatus())
                .message(message)
                .build();
        return ResponseEntity.status(status.getStatus()).body(new UniversalResponse<T>(error));

    }
}
