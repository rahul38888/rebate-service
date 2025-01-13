package com.speedybrand.rebate.service;

import com.speedybrand.rebate.pojo.Claim;

public interface IClaimService {

    Claim createClaim(final String transactionId);

}
