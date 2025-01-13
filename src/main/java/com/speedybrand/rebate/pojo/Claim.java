package com.speedybrand.rebate.pojo;

import com.speedybrand.rebate.utils.IdGenerator;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDateTime;

@Getter
@Setter
public class Claim {

    @BsonProperty(DbConstant.TRANSACTION_ID)
    private String transactionId;
    @BsonProperty(DbConstant.CLAIM_AMOUNT)
    private Double claimAmount;
    @BsonProperty(DbConstant.STATUS)
    private ClaimStatus status;
    @BsonProperty(DbConstant.CLAIM_DATE)
    private LocalDateTime claimDate;

    @BsonProperty(DbConstant.ID)
    public String getId() {
        String[] splits = transactionId.split(":");
        return IdGenerator.getClaimId(splits[splits.length - 1]);
    }

    public static class DbConstant {
        private DbConstant() {}

        public static final String ID = "_id";
        public static final String TRANSACTION_ID = "tid";
        public static final String CLAIM_AMOUNT = "cla";
        public static final String STATUS = "st";
        public static final String CLAIM_DATE = "cd";
    }
}
