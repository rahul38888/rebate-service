package com.speedybrand.rebate.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    public static class Constant {
        private Constant() {}

        public static final String PROGRAM_NAME = "programName";
        public static final String REBATE_PERCENTAGE = "rebatePercentage";
        public static final String START_DATE = "startDate";
        public static final String END_DATE = "endDate";
        public static final String ELIGIBILITY_CRITERIA = "eligibilityCriteria";
    }
}
