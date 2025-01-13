package com.speedybrand.rebate.pojo;

import com.speedybrand.rebate.models.requests.RebateProgramRequest;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDateTime;

@Getter
@Setter
public class RebateProgram {

    @BsonId
    private String id;
    @BsonProperty(DbConstant.PROGRAM_NAME)
    private String programName;
    @BsonProperty(DbConstant.REBATE_PERCENTAGE)
    private Double rebatePercentage;
    @BsonProperty(DbConstant.START_DATE)
    private LocalDateTime startDate;
    @BsonProperty(DbConstant.END_DATE)
    private LocalDateTime endDate;
    @BsonProperty(DbConstant.ELIGIBILITY_CRITERIA)
    private String eligibilityCriteria;

    @BsonProperty(DbConstant.CREATED_AT)
    private LocalDateTime createdAt;

    public RebateProgram() {}

    public static RebateProgram from(final RebateProgramRequest request) {
        final RebateProgram result = new RebateProgram();
        result.setProgramName(request.getProgramName());
        result.setRebatePercentage(request.getRebatePercentage());
        result.setStartDate(request.getStartDate());
        result.setEndDate(request.getEndDate());
        result.setEligibilityCriteria(request.getEligibilityCriteria());

        return result;
    }

    static class DbConstant {
        private DbConstant() {}


        public static final String ID = "_id";
        public static final String PROGRAM_NAME = "pn";
        public static final String REBATE_PERCENTAGE = "rp";
        public static final String START_DATE = "sd";
        public static final String END_DATE = "ed";
        public static final String ELIGIBILITY_CRITERIA = "ec";
        public static final String CREATED_AT = "cd";
    }
}
