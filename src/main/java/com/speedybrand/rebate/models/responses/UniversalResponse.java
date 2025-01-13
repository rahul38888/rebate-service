package com.speedybrand.rebate.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.speedybrand.rebate.models.responses.error.ErrorResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UniversalResponse<T> implements Serializable {
    private T response;
    private ErrorResponse error;

    public UniversalResponse(T response) {
        this.response = response;
    }

    public UniversalResponse(ErrorResponse error) {
        this.error = error;
    }

    public UniversalResponse(T response, ErrorResponse error) {
        this.response = response;
        this.error = error;
    }
}
