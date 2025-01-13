package com.speedybrand.rebate.service.impls;

import com.speedybrand.rebate.models.requests.TransactionRequest;
import com.speedybrand.rebate.pojo.RebateProgram;
import com.speedybrand.rebate.pojo.Transaction;
import com.speedybrand.rebate.repo.IRebateProgramRepo;
import com.speedybrand.rebate.repo.ITransactionRepo;
import com.speedybrand.rebate.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TransactionService implements ITransactionService {

    private final IRebateProgramRepo rebateProgramRepo;
    private final ITransactionRepo transactionRepo;

    @Autowired
    public TransactionService(final IRebateProgramRepo rebateProgramRepo, final ITransactionRepo transactionRepo) {
        this.rebateProgramRepo = rebateProgramRepo;
        this.transactionRepo = transactionRepo;
    }

    @Override
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
