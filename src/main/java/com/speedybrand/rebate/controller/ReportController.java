package com.speedybrand.rebate.controller;

import com.speedybrand.rebate.exceptions.InputValidationException;
import com.speedybrand.rebate.exceptions.RebateServiceException;
import com.speedybrand.rebate.models.responses.ClaimReportResponse;
import com.speedybrand.rebate.models.responses.UniversalResponse;
import com.speedybrand.rebate.models.responses.error.ErrorResponseFactory;
import com.speedybrand.rebate.service.IReportService;
import com.speedybrand.rebate.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController("reportController")
@RequestMapping(ReportController.ROOT_PATH)
public class ReportController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

    public static final String ROOT_PATH = "/report";
    public static final String CLAIMS_PATH = "/claims";

    public static final String FROM = "from";
    public static final String TO = "to";

    private final IReportService service;

    @Autowired
    public ReportController(final IReportService service) {
        this.service = service;
    }

    @GetMapping(value = ReportController.CLAIMS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UniversalResponse<ClaimReportResponse>> getReportForClaim(
            @RequestParam(FROM) final String from, @RequestParam(TO) final String to) {

        try {
            final LocalDateTime fromTime = CommonUtil.parseLocalDateTime(from);
            final LocalDateTime toTime = CommonUtil.parseLocalDateTime(to);
            validateInput(fromTime, toTime);
            return ResponseEntity.ok(new UniversalResponse<>(service.getClaimsReport(fromTime, toTime)));
        }
        catch (final RebateServiceException ex) {
            LOGGER.error("Exception during getReportForClaim call", ex);
            return ErrorResponseFactory.from(ex);
        }
        catch (final Exception ex) {
            LOGGER.error("Exception during getReportForClaim call", ex);
            return ErrorResponseFactory.fromException(ex);
        }
    }

    private void validateInput(final LocalDateTime from, final LocalDateTime to) {
        if(from.isAfter(to)) {
            throw new InputValidationException(List.of(FROM, TO), null);
        }
    }
}
