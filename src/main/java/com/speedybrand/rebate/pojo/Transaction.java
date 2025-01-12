package com.speedybrand.rebate.pojo;

import com.speedybrand.rebate.models.requests.TransactionRequest;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;

import java.time.LocalDateTime;

@Getter
@Setter
public class Transaction {

    @BsonId
    private String id;
    private Double amount;
    private LocalDateTime transactionDate;
    private String rebateProgramId;

    private LocalDateTime recordedAt;

    public Transaction() {}

    public static Transaction from(final TransactionRequest transaction) {
        final Transaction result = new Transaction();
        result.setId(transaction.getId());
        result.setAmount(transaction.getAmount());
        result.setTransactionDate(transaction.getTransactionDate());
        result.setRebateProgramId(transaction.getRebateProgramId());

        return result;
    }
}
