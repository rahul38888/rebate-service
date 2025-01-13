package com.speedybrand.rebate.repo;

import com.speedybrand.rebate.pojo.Claim;
import com.speedybrand.rebate.pojo.Statistics;
import org.bson.conversions.Bson;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IClaimRepo {

    Claim get(final String claimId);

    Claim create(final Claim claim);

    List<Statistics> getClaimAggregationReport(final LocalDateTime from, final LocalDateTime to);

    boolean updateProperties(Bson filter, Map<String, Object> updates);
}
