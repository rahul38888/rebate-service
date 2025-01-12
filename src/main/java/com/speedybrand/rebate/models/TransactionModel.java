package com.speedybrand.rebate.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
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
}
