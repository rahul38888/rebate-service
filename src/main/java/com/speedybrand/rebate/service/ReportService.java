package com.speedybrand.rebate.service;

import com.speedybrand.rebate.models.responses.ClaimReportResponse;
import com.speedybrand.rebate.models.responses.StatisticsResponse;
import com.speedybrand.rebate.pojo.Statistics;
import com.speedybrand.rebate.pojo.ClaimStatus;
import com.speedybrand.rebate.repo.IClaimRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final IClaimRepo claimRepo;

    public ReportService(final IClaimRepo claimRepo) {
        this.claimRepo = claimRepo;
    }


    public ClaimReportResponse getClaimsReport(final LocalDateTime from, final LocalDateTime to) {
        final List<Statistics> claimStats = claimRepo.getClaimAggrigattionReport(from, to);

        final Map<ClaimStatus, StatisticsResponse> claimStatsMap = new HashMap<>();
        final StatisticsResponse overalls = StatisticsResponse.builder()
                .total(0D)
                .count(0).build();

        claimStats.forEach(statistics -> {
            claimStatsMap.put(statistics.getStatus(), StatisticsResponse.from(statistics));
            overalls.setTotal(overalls.getTotal() + statistics.getTotal());
            overalls.setCount(overalls.getCount() + statistics.getCount());
        });

        return ClaimReportResponse.builder()
                .grouped(claimStatsMap)
                .overall(overalls)
                .build();
    }

}
