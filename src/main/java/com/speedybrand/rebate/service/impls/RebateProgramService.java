package com.speedybrand.rebate.service.impls;

import com.speedybrand.rebate.models.requests.RebateProgramRequest;
import com.speedybrand.rebate.models.responses.RebateCalculation;
import com.speedybrand.rebate.models.responses.RebateProgramResponse;
import com.speedybrand.rebate.models.responses.TransactionResponse;
import com.speedybrand.rebate.pojo.RebateProgram;
import com.speedybrand.rebate.pojo.Transaction;
import com.speedybrand.rebate.repo.IRebateProgramRepo;
import com.speedybrand.rebate.repo.ITransactionRepo;
import com.speedybrand.rebate.service.IRebateProgramService;
import com.speedybrand.rebate.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RebateProgramService implements IRebateProgramService {

    private final IRebateProgramRepo rebateProgramRepo;
    private final ITransactionRepo transactionRepo;

    @Autowired
    public RebateProgramService(final IRebateProgramRepo rebateProgramRepo, final ITransactionRepo transactionRepo) {
        this.rebateProgramRepo = rebateProgramRepo;
        this.transactionRepo = transactionRepo;
    }

    @Override
    public RebateProgram getRebateProgram(final String rebateProgramId) {
        return rebateProgramRepo.get(rebateProgramId);
    }

    @Override
    public RebateProgram createRebateProgram(final RebateProgramRequest request) {
        return rebateProgramRepo.create(RebateProgram.from(request));
    }

    @Override
    public RebateCalculation calculateRebate(final String transactionId) {
        final Transaction transaction = transactionRepo.get(transactionId);
        final RebateProgram rebateProgram = getRebateProgram(transaction.getRebateProgramId());

        return RebateCalculation.builder()
                .transaction(TransactionResponse.from(transaction))
                .rebateProgram(RebateProgramResponse.from(rebateProgram))
                .amount(CommonUtil.calculateRebateAmount(transaction.getAmount(), rebateProgram.getRebatePercentage()))
                .build();
    }
}
