package com.speedybrand.rebate.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Getter
@Setter
public class Statistics {

    @BsonId
    private ClaimStatus status;
    @BsonProperty(DbConstant.TOTAL)
    private Double total;
    @BsonProperty(DbConstant.COUNT)
    private Integer count;

    public Statistics() {}

    @Builder
    public Statistics(ClaimStatus status, Double total, Integer count) {
        this.status = status;
        this.total = total;
        this.count = count;
    }

    public static class DbConstant {
        private DbConstant() {}

        public static final String ID = "_id";
        public static final String TOTAL = "total";
        public static final String COUNT = "count";
    }
}
