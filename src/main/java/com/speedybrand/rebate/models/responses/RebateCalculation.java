package com.speedybrand.rebate.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RebateCalculation implements Serializable {

    private TransactionResponse transaction;
    private RebateProgramResponse rebateProgram;
    private Double amount;

    static class Constant {
        private Constant() {}

        public static final String TRANSACTION = "transaction";
        public static final String REBATE_PROGRAM = "rebateProgram";
        public static final String AMOUNT = "amount";
    }

}
