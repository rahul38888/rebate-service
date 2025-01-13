package com.speedybrand.rebate.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.speedybrand.rebate.pojo.ClaimStatus;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClaimReportResponse implements Serializable {

    private Map<ClaimStatus, StatisticsResponse> grouped;
    private StatisticsResponse overall;
    private LocalDateTime from;
    private LocalDateTime to;

    static class Constant {
        private Constant() {}

        public static final String GROUPED = "grouped";
        public static final String OVERALL = "overall";
        public static final String FROM = "from";
        public static final String TO = "to";
    }
}
