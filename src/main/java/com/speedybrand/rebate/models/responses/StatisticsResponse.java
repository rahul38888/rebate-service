package com.speedybrand.rebate.models.responses;

import com.speedybrand.rebate.pojo.Statistics;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
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
}
