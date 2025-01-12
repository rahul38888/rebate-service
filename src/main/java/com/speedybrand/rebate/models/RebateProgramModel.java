package com.speedybrand.rebate.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class RebateProgramModel implements Serializable {

    private final String programName;
    private final Double rebatePercentage;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String eligibilityCriteria;

    public RebateProgramModel(final String programName, final Double rebatePercentage, final LocalDateTime startDate,
                              final LocalDateTime endDate, final String eligibilityCriteria) {
        this.programName = programName;
        this.rebatePercentage = rebatePercentage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.eligibilityCriteria = eligibilityCriteria;
    }
}
