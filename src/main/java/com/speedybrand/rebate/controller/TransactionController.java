package com.speedybrand.rebate.controller;

import com.speedybrand.rebate.models.requests.TransactionRequest;
import com.speedybrand.rebate.models.responses.TransactionResponse;
import com.speedybrand.rebate.pojo.Transaction;
import com.speedybrand.rebate.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController("transactionController")
@RequestMapping(TransactionController.ROOT_PATH)
public class TransactionController {
    public static final String ROOT_PATH = "/transaction";
    public static final String RECORD_PATH = "/record";

    private final TransactionService service;

    @Autowired
    public TransactionController(final TransactionService service) {
        this.service = service;
    }

    @PostMapping(value = TransactionController.RECORD_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<TransactionResponse> recordTransaction(@RequestBody final TransactionRequest request) {
        final Transaction transaction = service.recordTransaction(request);
        if (Objects.isNull(transaction)) {
           ResponseEntity.badRequest().body("Invalid Transaction");
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TransactionResponse.from(transaction));
    }
}
