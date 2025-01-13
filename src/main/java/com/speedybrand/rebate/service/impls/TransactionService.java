package com.speedybrand.rebate.service.impls;

import com.speedybrand.rebate.exceptions.InputValidationException;
import com.speedybrand.rebate.models.requests.TransactionRequest;
import com.speedybrand.rebate.pojo.RebateProgram;
import com.speedybrand.rebate.pojo.Transaction;
import com.speedybrand.rebate.repo.IRebateProgramRepo;
import com.speedybrand.rebate.repo.ITransactionRepo;
import com.speedybrand.rebate.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TransactionService implements ITransactionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    private final IRebateProgramRepo rebateProgramRepo;
    private final ITransactionRepo transactionRepo;

    @Autowired
    public TransactionService(final IRebateProgramRepo rebateProgramRepo, final ITransactionRepo transactionRepo) {
        this.rebateProgramRepo = rebateProgramRepo;
        this.transactionRepo = transactionRepo;
    }

    @Override
    public Transaction recordTransaction(final TransactionRequest transaction) {
        isValid(transaction);
        return transactionRepo.record(Transaction.from(transaction));
    }

    private void isValid(final TransactionRequest transaction) {
        final RebateProgram rebateProgram = rebateProgramRepo.get(transaction.getRebateProgramId());
        if (Objects.isNull(rebateProgram)) {
            LOGGER.error("Incorrect rebate program id stored for transaction, transactionId = {}, rebateProgramId = {}",
                    transaction.getId(), transaction.getRebateProgramId());
            throw new InputValidationException("rebateProgramId is invalid", null);
        }
        if (transaction.getTransactionDate().isBefore(rebateProgram.getStartDate()) ||
                transaction.getTransactionDate().isAfter(rebateProgram.getEndDate())) {
            LOGGER.error("Transaction date is out of rebate program window, transactionId = {}, rebateProgramId = {}",
                    transaction.getId(), transaction.getRebateProgramId());
            throw new InputValidationException("transactionDate is out of rebate window", null);
        }
    }
}
