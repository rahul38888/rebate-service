package com.speedybrand.rebate.service;

import com.speedybrand.rebate.models.requests.TransactionRequest;
import com.speedybrand.rebate.pojo.RebateProgram;
import com.speedybrand.rebate.pojo.Transaction;
import com.speedybrand.rebate.repo.IRebateProgramRepo;
import com.speedybrand.rebate.repo.ITransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TransactionService {

    private final IRebateProgramRepo rebateProgramRepo;
    private final ITransactionRepo transactionRepo;

    @Autowired
    public TransactionService(final IRebateProgramRepo rebateProgramRepo, final ITransactionRepo transactionRepo) {
        this.rebateProgramRepo = rebateProgramRepo;
        this.transactionRepo = transactionRepo;
    }

    public Transaction recordTransaction(final TransactionRequest transaction) {
        if (!isValid(transaction)) {
            // TODO: Throw exception
            return null;
        }
        return transactionRepo.record(Transaction.from(transaction));
    }

    private boolean isValid(final TransactionRequest transaction) {
        final RebateProgram rebateProgram = rebateProgramRepo.get(transaction.getRebateProgramId());

        return Objects.nonNull(rebateProgram) &&
                transaction.getTransactionDate().isAfter(rebateProgram.getStartDate()) &&
                transaction.getTransactionDate().isBefore(rebateProgram.getEndDate());
    }
}
