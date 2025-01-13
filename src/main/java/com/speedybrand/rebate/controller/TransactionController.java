package com.speedybrand.rebate.controller;

import com.speedybrand.rebate.exceptions.RebateServiceException;
import com.speedybrand.rebate.models.TransactionModel;
import com.speedybrand.rebate.models.requests.TransactionRequest;
import com.speedybrand.rebate.models.responses.TransactionResponse;
import com.speedybrand.rebate.models.responses.UniversalResponse;
import com.speedybrand.rebate.models.responses.error.ErrorResponseFactory;
import com.speedybrand.rebate.pojo.Transaction;
import com.speedybrand.rebate.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("transactionController")
@RequestMapping(TransactionController.ROOT_PATH)
public class TransactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    public static final String ROOT_PATH = "/transaction";
    public static final String RECORD_PATH = "/record";

    private final ITransactionService service;

    @Autowired
    public TransactionController(final ITransactionService service) {
        this.service = service;
    }

    @PostMapping(value = TransactionController.RECORD_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UniversalResponse<TransactionResponse>> recordTransaction(
            @RequestBody final TransactionRequest request) {

        try {
            final Transaction transaction = service.recordTransaction(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .header(TransactionModel.Constant.ID, transaction.getId())
                    .body(new UniversalResponse<>(TransactionResponse.from(transaction)));
        }
        catch (final RebateServiceException ex) {
            LOGGER.error("Exception during recordTransaction call", ex);
            return ErrorResponseFactory.from(ex);
        }
        catch (final Exception ex) {
            LOGGER.error("Exception during recordTransaction call", ex);
            return ErrorResponseFactory.fromException(ex);
        }
    }
}
