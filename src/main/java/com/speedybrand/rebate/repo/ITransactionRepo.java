package com.speedybrand.rebate.repo;

import com.speedybrand.rebate.pojo.Transaction;

public interface ITransactionRepo {

    Transaction get(final String transactionId);

    Transaction record(final Transaction rebateProgram);
}
