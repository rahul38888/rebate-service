package com.speedybrand.rebate.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.speedybrand.rebate.pojo.Claim;
import com.speedybrand.rebate.pojo.ClaimStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClaimResponse implements Serializable {

    private String id;
    private String transactionId;
    private Double claimAmount;
    private ClaimStatus status;
    private LocalDateTime claimDate;

    public ClaimResponse(final String id, final String transactionId, final Double claimAmount,
                         final ClaimStatus status, final LocalDateTime claimDate) {
        this.id = id;
        this.transactionId = transactionId;
        this.claimAmount = claimAmount;
        this.status = status;
        this.claimDate = claimDate;
    }

    public static ClaimResponse from(final Claim claim) {
        return ClaimResponse.builder().
                id(claim.getId()).
                transactionId(claim.getTransactionId()).
                claimAmount(claim.getClaimAmount()).
                status(claim.getStatus()).
                claimDate(claim.getClaimDate()).
                build();
    }

    public static class Constant {
        private Constant() {}

        public static final String ID = "id";
        public static final String TRANSACTION_ID = "transactionId";
        public static final String CLAIM_AMOUNT = "claimAmount";
        public static final String STATUS = "status";
        public static final String CLAIM_DATE = "claimDate";
    }
}
