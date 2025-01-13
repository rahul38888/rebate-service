package com.speedybrand.rebate.service;

import com.speedybrand.rebate.models.requests.RebateProgramRequest;
import com.speedybrand.rebate.models.responses.RebateCalculation;
import com.speedybrand.rebate.pojo.RebateProgram;

public interface IRebateProgramService {

    RebateProgram getRebateProgram(final String rebateProgramId);

    RebateProgram createRebateProgram(final RebateProgramRequest request);

    RebateCalculation calculateRebate(final String transactionId);
}
