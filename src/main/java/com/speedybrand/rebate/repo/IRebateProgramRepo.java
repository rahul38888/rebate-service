package com.speedybrand.rebate.repo;

import com.speedybrand.rebate.pojo.RebateProgram;

public interface IRebateProgramRepo {

    RebateProgram get(final String rebateProgramId);

    RebateProgram create(final RebateProgram rebateProgram);
}
