package com.speedybrand.rebate.pojo;

import com.speedybrand.rebate.utils.IdGenerator;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDateTime;

@Getter
@Setter
public class Claim {
    private String transactionId;
    private Double claimAmount;
    private ClaimStatus status;
    private LocalDateTime claimDate;

    @BsonProperty("_id")
    public String getId() {
        String[] splits = transactionId.split(":");
        return IdGenerator.getClaimId(splits[splits.length - 1]);
    }

    public static void main(String[] args) {
        Claim c = new Claim();
        c.setTransactionId("arn:txn:3dd9fae5-5c1e-409a-a29c-b1ec68ebc871");
        System.out.printf(c.getId());
    }
}
