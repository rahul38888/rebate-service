package com.speedybrand.rebate.controller;

import com.speedybrand.rebate.exceptions.InputValidationException;
import com.speedybrand.rebate.exceptions.RebateServiceException;
import com.speedybrand.rebate.models.requests.RebateProgramRequest;
import com.speedybrand.rebate.models.responses.RebateCalculation;
import com.speedybrand.rebate.models.responses.RebateProgramResponse;
import com.speedybrand.rebate.models.responses.UniversalResponse;
import com.speedybrand.rebate.models.responses.error.ErrorResponseFactory;
import com.speedybrand.rebate.service.IRebateProgramService;
import com.speedybrand.rebate.pojo.RebateProgram;
import lombok.NonNull;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController("rebateProgramController")
@RequestMapping(RebateProgramController.ROOT_PATH)
public class RebateProgramController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RebateProgramController.class);

    public static final String ROOT_PATH = "/rebate";
    public static final String GET_PATH = "/{rebateProgramId}";
    public static final String CREATE_PATH = "/create";
    public static final String CALCULATE_PATH = "/calculate/{transactionId}";

    private final IRebateProgramService service;

    @Autowired
    public RebateProgramController(final IRebateProgramService service) {
        this.service = service;
    }

    @GetMapping(value = RebateProgramController.GET_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UniversalResponse<RebateProgramResponse>> getRebateProgram(@PathVariable final String rebateProgramId) {
        try {
            final RebateProgram rebateProgram = service.getRebateProgram(rebateProgramId);
            return ResponseEntity.ok(new UniversalResponse<>(RebateProgramResponse.from(rebateProgram)));
        }
        catch (final RebateServiceException ex) {
            LOGGER.error("Exception during getRebateProgram call", ex);
            return ErrorResponseFactory.from(ex);
        }
        catch (final Exception ex) {
            LOGGER.error("Exception during getRebateProgram call", ex);
            return ErrorResponseFactory.fromException(ex);
        }

    }

    @PostMapping(value = RebateProgramController.CREATE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UniversalResponse<RebateProgramResponse>> createRebateProgram(
            @RequestBody final RebateProgramRequest request) {

        try {
            isValidRebateProgramRequest(request);
            final RebateProgram rebateProgram = service.createRebateProgram(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header(RebateProgramResponse.Constant.ID, rebateProgram.getId())
                    .body(new UniversalResponse<>(RebateProgramResponse.from(rebateProgram)));
        }
        catch (final RebateServiceException ex) {
            LOGGER.error("Exception during createRebateProgram call", ex);
            return ErrorResponseFactory.from(ex);
        }
        catch (final Exception ex) {
            LOGGER.error("Exception during createRebateProgram call", ex);
            return ErrorResponseFactory.fromException(ex);
        }

    }

    private void isValidRebateProgramRequest(@NonNull final RebateProgramRequest request) throws RebateServiceException {

        if (StringUtils.isEmpty(request.getProgramName()) ) {
            throw new InputValidationException("programName", null);
        }
        if (request.getRebatePercentage() <= 0) {
            throw new InputValidationException("rebatePercentage", null);
        }
        if (Objects.isNull(request.getStartDate()) || Objects.isNull(request.getEndDate()) ||
                request.getStartDate().isAfter(request.getEndDate())) {
            throw new InputValidationException(List.of("startDate", "endDate"), null);
        }
    }

    @GetMapping(value = RebateProgramController.CALCULATE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UniversalResponse<RebateCalculation>> calculateRebate(@PathVariable final String transactionId) {

        try {
            return ResponseEntity.ok(new UniversalResponse<>(service.calculateRebate(transactionId)));
        }
        catch (final RebateServiceException ex) {
            LOGGER.error("Exception during calculateRebate call", ex);
            return ErrorResponseFactory.from(ex);
        }
        catch (final Exception ex) {
            LOGGER.error("Exception during calculateRebate call", ex);
            return ErrorResponseFactory.fromException(ex);
        }
    }
}
