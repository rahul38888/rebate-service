package com.speedybrand.rebate.service;

import com.speedybrand.rebate.models.responses.ClaimReportResponse;

import java.time.LocalDateTime;

public interface IReportService {

    ClaimReportResponse getClaimsReport(final LocalDateTime from, final LocalDateTime to);

}
