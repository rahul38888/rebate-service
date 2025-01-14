package com.speedybrand.rebate.controller;

import com.speedybrand.rebate.models.responses.ClaimResponse;
import com.speedybrand.rebate.models.responses.UniversalResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("healthcheck")
public class HealthCheck {

    public static final String PING = "/ping";
    public static final String HEALTHCHECK_OUTPUT = "pong";

    @GetMapping(value = HealthCheck.PING, produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok(HEALTHCHECK_OUTPUT);
    }
}
