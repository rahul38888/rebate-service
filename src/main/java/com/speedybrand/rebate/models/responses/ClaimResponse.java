package com.speedybrand.rebate.models.responses;

import com.speedybrand.rebate.pojo.Claim;
import com.speedybrand.rebate.pojo.ClaimStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ClaimResponse {

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
}
