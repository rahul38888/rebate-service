package com.speedybrand.rebate.service;

import com.speedybrand.rebate.pojo.Claim;

public interface IClaimService {

    Claim getClaim(final String claimId);

    Claim createClaim(final String transactionId);

    void approve(final String claimId);

    void reject(final String claimId);

}
