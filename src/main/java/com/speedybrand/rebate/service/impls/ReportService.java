package com.speedybrand.rebate.service.impls;

import com.speedybrand.rebate.models.responses.ClaimReportResponse;
import com.speedybrand.rebate.models.responses.StatisticsResponse;
import com.speedybrand.rebate.pojo.Statistics;
import com.speedybrand.rebate.pojo.ClaimStatus;
import com.speedybrand.rebate.repo.IClaimRepo;
import com.speedybrand.rebate.service.IReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService implements IReportService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportService.class);

    private final IClaimRepo claimRepo;

    public ReportService(final IClaimRepo claimRepo) {
        this.claimRepo = claimRepo;
    }


    @Override
    public ClaimReportResponse getClaimsReport(final LocalDateTime from, final LocalDateTime to) {
        final List<Statistics> claimStats = claimRepo.getClaimAggregationReport(from, to);

        final Map<ClaimStatus, StatisticsResponse> claimStatsMap = new HashMap<>();
        final StatisticsResponse overalls = StatisticsResponse.builder()
                .total(0D).count(0).build();

        claimStats.forEach(statistics -> {
            claimStatsMap.put(statistics.getStatus(), StatisticsResponse.from(statistics));
            overalls.setTotal(overalls.getTotal() + statistics.getTotal());
            overalls.setCount(overalls.getCount() + statistics.getCount());
        });

        return ClaimReportResponse.builder()
                .grouped(claimStatsMap)
                .overall(overalls)
                .from(from).to(to)
                .build();
    }

}
