package com.speedybrand.rebate.models.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.speedybrand.rebate.models.RebateProgramModel;
import com.speedybrand.rebate.pojo.RebateProgram;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RebateProgramResponse extends RebateProgramModel implements Serializable {

    private final String id;
    private final LocalDateTime createdAt;

    public RebateProgramResponse(final String programName, final Double rebatePercentage, final LocalDateTime startDate,
                                 final LocalDateTime endDate, final String eligibilityCriteria, final String id,
                                 final LocalDateTime createdAt) {
        super(programName, rebatePercentage, startDate, endDate, eligibilityCriteria);
        this.id = id;
        this.createdAt = createdAt;
    }

    public static RebateProgramResponse from(final RebateProgram rebateProgram) {
        return RebateProgramResponse.builder()
                .id(rebateProgram.getId())
                .programName(rebateProgram.getProgramName())
                .rebatePercentage(rebateProgram.getRebatePercentage())
                .startDate(rebateProgram.getStartDate())
                .endDate(rebateProgram.getEndDate())
                .eligibilityCriteria(rebateProgram.getEligibilityCriteria())
                .createdAt(rebateProgram.getCreatedAt())
                .build();
    }

    public static class Constant {
        private Constant() {}

        public static final String ID = "id";
        public static final String CREATED_AT = "createdAt";
    }
}
