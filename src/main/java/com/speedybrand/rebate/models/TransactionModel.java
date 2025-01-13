package com.speedybrand.rebate.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionModel implements Serializable {
    private String id;
    private Double amount;
    private LocalDateTime transactionDate;
    private String rebateProgramId;

    public TransactionModel(final String id, final Double amount, final LocalDateTime transactionDate,
                            final String rebateProgramId) {
        this.id = id;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.rebateProgramId = rebateProgramId;
    }

    public static class Constant {

        public Constant() {}

        public static final String ID = "id";
        public static final String AMOUNT = "amount";
        public static final String TRANSACTION_DATE = "transactionDate";
        public static final String REBATE_PROGRAM_ID = "rebateProgramId";
    }
}
