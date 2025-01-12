package com.speedybrand.rebate.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;

@Getter
@Setter
public class Statistics {

    @BsonId
    private ClaimStatus status;
    private Double total;
    private Integer count;

    public Statistics() {}

    @Builder
    public Statistics(ClaimStatus status, Double total, Integer count) {
        this.status = status;
        this.total = total;
        this.count = count;
    }
}
