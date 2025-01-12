package com.speedybrand.rebate.controller;

import com.speedybrand.rebate.models.requests.RebateProgramRequest;
import com.speedybrand.rebate.models.responses.RebateCalculation;
import com.speedybrand.rebate.models.responses.RebateProgramResponse;
import com.speedybrand.rebate.pojo.RebateProgram;
import com.speedybrand.rebate.service.RebateProgramService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.speedybrand.rebate.pojo.Constants.REBATE_PROGRAM_ID;

@RestController("rebateProgramController")
@RequestMapping(RebateProgramController.ROOT_PATH)
public class RebateProgramController {

    public static final String ROOT_PATH = "/rebate";
    public static final String GET_PATH = "/{rebateProgramId}";
    public static final String CREATE_PATH = "/create";
    public static final String CALCULATE_PATH = "/calculate/{transactionId}";

    private final RebateProgramService service;

    @Autowired
    public RebateProgramController(final RebateProgramService service) {
        this.service = service;
    }

    @GetMapping(value = RebateProgramController.GET_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RebateProgramResponse> getRebateProgram(@PathVariable final String rebateProgramId) {
        final RebateProgram rebateProgram = service.getRebateProgram(rebateProgramId);
        return ResponseEntity.ok(RebateProgramResponse.from(rebateProgram));
    }

    @PostMapping(value = RebateProgramController.CREATE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RebateProgramResponse> createRebateProgram(@RequestBody final RebateProgramRequest request) {
        if (!isValidRebateProgramRequest(request)) {
            // TODO: return proper error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        final RebateProgram rebateProgram = service.createRebateProgram(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(REBATE_PROGRAM_ID, rebateProgram.getId())
                .body(RebateProgramResponse.from(rebateProgram));
    }

    private boolean isValidRebateProgramRequest(final RebateProgramRequest request) {
        return Objects.nonNull(request) && StringUtils.isNotEmpty(request.getProgramName()) &&
                (request.getRebatePercentage() > 0) &&
                (Objects.nonNull(request.getStartDate())) &&
                (Objects.nonNull(request.getEndDate())) &&
                (request.getStartDate().isBefore(request.getEndDate()));
    }

    @GetMapping(value = RebateProgramController.CALCULATE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RebateCalculation> calculateRebate(@PathVariable final String transactionId) {
        return ResponseEntity.ok(service.calculateRebate(transactionId));
    }
}
