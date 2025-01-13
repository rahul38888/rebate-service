package com.speedybrand.rebate.models.requests;

import com.speedybrand.rebate.models.RebateProgramModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class RebateProgramRequest extends RebateProgramModel implements Serializable {

    public RebateProgramRequest(final String programName, final Double rebatePercentage, final LocalDateTime startDate,
                                final LocalDateTime endDate, final String eligibilityCriteria) {
        super(programName, rebatePercentage, startDate, endDate, eligibilityCriteria);
    }

}
