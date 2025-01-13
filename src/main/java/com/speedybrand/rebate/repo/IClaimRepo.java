package com.speedybrand.rebate.repo;

import com.speedybrand.rebate.pojo.Claim;
import com.speedybrand.rebate.pojo.Statistics;

import java.time.LocalDateTime;
import java.util.List;

public interface IClaimRepo {

    Claim create(final Claim claim);

    List<Statistics> getClaimAggregationReport(final LocalDateTime from, final LocalDateTime to);
}
