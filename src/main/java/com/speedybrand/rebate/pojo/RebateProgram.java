package com.speedybrand.rebate.pojo;

import com.speedybrand.rebate.models.requests.RebateProgramRequest;
import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;

import java.time.LocalDateTime;

@Getter
@Setter
public class RebateProgram {

    @BsonId
    private String id;
    private String programName;
    private Double rebatePercentage;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String eligibilityCriteria;

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
}
