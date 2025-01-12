package com.speedybrand.rebate.controller;

import com.speedybrand.rebate.models.responses.ClaimReportResponse;
import com.speedybrand.rebate.service.ReportService;
import com.speedybrand.rebate.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController("reportController")
@RequestMapping(ReportController.ROOT_PATH)
public class ReportController {
    public static final String ROOT_PATH = "/report";
    public static final String CLAIMS_PATH = "/claims";

    private final ReportService service;

    @Autowired
    public ReportController(final ReportService service) {
        this.service = service;
    }

    @PostMapping(value = ReportController.CLAIMS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ClaimReportResponse> getReportForClaim(@RequestParam("from") final String from,
                                                                 @RequestParam("to") final String to) {

        final LocalDateTime fromTime = CommonUtil.parseLocalDateTime(from);
        final LocalDateTime toTime = CommonUtil.parseLocalDateTime(to);

        if(fromTime.isBefore(toTime)) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(service.getClaimsReport(fromTime, toTime));
    }
}
