package com.speedybrand.rebate.models.responses;

import com.speedybrand.rebate.models.TransactionModel;
import com.speedybrand.rebate.pojo.Transaction;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class TransactionResponse extends TransactionModel implements Serializable {

    private final LocalDateTime recordedAt;

    public TransactionResponse(final String id, final Double amount, final LocalDateTime transactionDate,
                               final String rebateProgramId, final LocalDateTime recordedAt) {
        super(id, amount, transactionDate, rebateProgramId);
        this.recordedAt = recordedAt;
    }

    public static TransactionResponse from(final Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .transactionDate(transaction.getTransactionDate())
                .rebateProgramId(transaction.getRebateProgramId())
                .recordedAt(transaction.getRecordedAt())
                .build();
    }
}
