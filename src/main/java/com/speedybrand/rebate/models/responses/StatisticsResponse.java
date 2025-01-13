package com.speedybrand.rebate.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.speedybrand.rebate.pojo.Statistics;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatisticsResponse implements Serializable {
    private Double total;
    private Integer count;

    public StatisticsResponse() {}

    @Builder
    public StatisticsResponse(Double total, Integer count) {
        this.total = total;
        this.count = count;
    }

    public static StatisticsResponse from(Statistics statistics) {
        return StatisticsResponse.builder()
                .total(statistics.getTotal())
                .count(statistics.getCount())
                .build();
    }

    static class Constant {
        private Constant() {}

        public static final String TOTAL = "total";
        public static final String COUNT = "count";
    }
}
