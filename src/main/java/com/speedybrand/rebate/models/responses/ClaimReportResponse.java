package com.speedybrand.rebate.models.responses;

import com.speedybrand.rebate.pojo.ClaimStatus;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Builder
public class ClaimReportResponse implements Serializable {

    private Map<ClaimStatus, StatisticsResponse> grouped;
    private StatisticsResponse overall;

}
