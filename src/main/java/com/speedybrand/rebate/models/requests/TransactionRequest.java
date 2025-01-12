package com.speedybrand.rebate.models.requests;

import com.speedybrand.rebate.models.TransactionModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class TransactionRequest extends TransactionModel implements Serializable {

    public TransactionRequest(final String id, final Double amount, final LocalDateTime transactionDate,
                              final String rebateProgramId) {
        super(id, amount, transactionDate, rebateProgramId);
    }

}
