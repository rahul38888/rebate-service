package com.speedybrand.rebate.controller;

import com.speedybrand.rebate.models.responses.ClaimResponse;
import com.speedybrand.rebate.pojo.Claim;
import com.speedybrand.rebate.service.IClaimService;
import com.speedybrand.rebate.service.impls.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("claimController")
@RequestMapping(ClaimController.ROOT_PATH)
public class ClaimController {
    public static final String ROOT_PATH = "/claim";
    public static final String CREATE_PATH = "/create";

    private final IClaimService service;

    @Autowired
    public ClaimController(final IClaimService service) {
        this.service = service;
    }

    @PostMapping(value = ClaimController.CREATE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ClaimResponse> createClaim(@RequestParam("tid") final String transactionId) {

        final Claim claim = service.createClaim(transactionId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ClaimResponse.from(claim));
    }
}
