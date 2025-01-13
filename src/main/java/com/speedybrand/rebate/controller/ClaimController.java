package com.speedybrand.rebate.controller;

import com.speedybrand.rebate.exceptions.RebateServiceException;
import com.speedybrand.rebate.models.responses.ClaimResponse;
import com.speedybrand.rebate.models.responses.UniversalResponse;
import com.speedybrand.rebate.models.responses.error.ErrorResponseFactory;
import com.speedybrand.rebate.pojo.Claim;
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
    public static final String CREATE_PATH = "/create";

    public static final String TRANSACTION_ID = "tid";

    private final IClaimService service;

    @Autowired
    public ClaimController(final IClaimService service) {
        this.service = service;
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
}
