package com.speedybrand.rebate.controller;

import com.speedybrand.rebate.exceptions.RebateServiceException;
import com.speedybrand.rebate.models.responses.ClaimResponse;
import com.speedybrand.rebate.models.responses.RebateProgramResponse;
import com.speedybrand.rebate.models.responses.UniversalResponse;
import com.speedybrand.rebate.models.responses.error.ErrorResponseFactory;
import com.speedybrand.rebate.pojo.Claim;
import com.speedybrand.rebate.pojo.RebateProgram;
import com.speedybrand.rebate.service.IClaimService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("claimController")
@RequestMapping(ClaimController.ROOT_PATH)
public class ClaimController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClaimController.class);

    public static final String ROOT_PATH = "/claim";
    public static final String GET_PATH = "/{claimId}";
    public static final String CREATE_PATH = "/create";
    public static final String APPROVE_PATH = "/{claimId}/approve";
    public static final String REJECT_PATH = "/{claimId}/reject";

    public static final String TRANSACTION_ID = "tid";

    private final IClaimService service;

    @Autowired
    public ClaimController(final IClaimService service) {
        this.service = service;
    }

    @GetMapping(value = ClaimController.GET_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UniversalResponse<ClaimResponse>> getClaim(@PathVariable final String claimId) {
        try {
            final Claim claim = service.getClaim(claimId);
            return ResponseEntity.status(HttpStatus.OK).
                    header(RebateProgramResponse.Constant.ID, claim.getId()).
                    body(new UniversalResponse<>(ClaimResponse.from(claim)));
        }
        catch (final RebateServiceException ex) {
            LOGGER.error("Exception during getClaim call", ex);
            return ErrorResponseFactory.from(ex);
        }
        catch (final Exception ex) {
            LOGGER.error("Exception during getClaim call", ex);
            return ErrorResponseFactory.fromException(ex);
        }

    }

    @PostMapping(value = ClaimController.CREATE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UniversalResponse<ClaimResponse>> createClaim(
            @RequestParam(TRANSACTION_ID) final String transactionId) {

        try {
            final Claim claim = service.createClaim(transactionId);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header(ClaimResponse.Constant.ID, claim.getId())
                    .body(new UniversalResponse<>(ClaimResponse.from(claim)));
        }
        catch (final RebateServiceException ex) {
            LOGGER.error("Exception during createClaim call", ex);
            return ErrorResponseFactory.from(ex);
        }
        catch (final Exception ex) {
            LOGGER.error("Exception during createClaim call", ex);
            return ErrorResponseFactory.fromException(ex);
        }
    }

    @PutMapping(value = ClaimController.APPROVE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UniversalResponse<Void>> approveClaim(@PathVariable final String claimId) {
        try {
            service.approve(claimId);
            return ResponseEntity.status(HttpStatus.OK)
                    .header(ClaimResponse.Constant.ID, claimId).build();
        }
        catch (final RebateServiceException ex) {
            LOGGER.error("Exception during claimApprove call", ex);
            return ErrorResponseFactory.from(ex);
        }
        catch (final Exception ex) {
            LOGGER.error("Exception during claimApprove call", ex);
            return ErrorResponseFactory.fromException(ex);
        }
    }

    @PutMapping(value = ClaimController.REJECT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UniversalResponse<Void>> rejectClim(@PathVariable final String claimId) {
        try {
            service.reject(claimId);
            return ResponseEntity.status(HttpStatus.OK)
                    .header(ClaimResponse.Constant.ID, claimId).build();
        }
        catch (final RebateServiceException ex) {
            LOGGER.error("Exception during claimReject call", ex);
            return ErrorResponseFactory.from(ex);
        }
        catch (final Exception ex) {
            LOGGER.error("Exception during claimReject call", ex);
            return ErrorResponseFactory.fromException(ex);
        }
    }

}
