package com.speedybrand.rebate.pojo;

import com.speedybrand.rebate.models.requests.TransactionRequest;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDateTime;

@Getter
@Setter
public class Transaction {

    @BsonId
    private String id;
    @BsonProperty(DbConstant.AMOUNT)
    private Double amount;
    @BsonProperty(DbConstant.TRANSACTION_DATE)
    private LocalDateTime transactionDate;
    @BsonProperty(DbConstant.REBATE_PROGRAM_ID)
    private String rebateProgramId;

    @BsonProperty(DbConstant.RECORDED_AT)
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

    public static class DbConstant {
        private DbConstant() {}

        public static final String ID = "_id";
        public static final String AMOUNT = "am";
        public static final String TRANSACTION_DATE = "td";
        public static final String REBATE_PROGRAM_ID = "rid";
        public static final String RECORDED_AT = "rd";
    }
}
