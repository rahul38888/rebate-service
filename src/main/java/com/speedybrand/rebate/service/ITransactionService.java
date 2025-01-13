package com.speedybrand.rebate.service;

import com.speedybrand.rebate.models.requests.TransactionRequest;
import com.speedybrand.rebate.pojo.Transaction;

public interface ITransactionService {

    Transaction recordTransaction(final TransactionRequest transaction);

}
