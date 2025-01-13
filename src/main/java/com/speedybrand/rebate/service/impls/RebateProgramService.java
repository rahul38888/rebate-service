package com.speedybrand.rebate.service.impls;

import com.speedybrand.rebate.exceptions.EntityNotFoundException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class RebateProgramService implements IRebateProgramService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RebateProgramService.class);

    private final IRebateProgramRepo rebateProgramRepo;
    private final ITransactionRepo transactionRepo;

    @Autowired
    public RebateProgramService(final IRebateProgramRepo rebateProgramRepo, final ITransactionRepo transactionRepo) {
        this.rebateProgramRepo = rebateProgramRepo;
        this.transactionRepo = transactionRepo;
    }

    @Override
    public RebateProgram getRebateProgram(final String rebateProgramId) {
        final RebateProgram rebateProgram = rebateProgramRepo.get(rebateProgramId);
        if (Objects.isNull(rebateProgram)) {
            LOGGER.error("Invalid rebate program id, id = {}", rebateProgramId);
            throw new EntityNotFoundException(Map.of(CommonUtil.REBATE_PROGRAM_ID, rebateProgramId), null);
        }
        return rebateProgramRepo.get(rebateProgramId);
    }

    @Override
    public RebateProgram createRebateProgram(final RebateProgramRequest request) {
        return rebateProgramRepo.create(RebateProgram.from(request));
    }

    @Override
    public RebateCalculation calculateRebate(final String transactionId) {
        final Transaction transaction = transactionRepo.get(transactionId);
        if (Objects.isNull(transaction)) {
            LOGGER.error("Invalid transaction id, id = {}", transactionId);
            throw new EntityNotFoundException(Map.of(CommonUtil.TRANSACTION_ID, transactionId), null);
        }
        final RebateProgram rebateProgram = getRebateProgram(transaction.getRebateProgramId());
        if (Objects.isNull(rebateProgram)) {
            LOGGER.error("Incorrect related rebate program id found in database, id = {}",
                    transaction.getRebateProgramId());
            throw new EntityNotFoundException(Map.of(CommonUtil.TRANSACTION_ID, transactionId,
                    CommonUtil.REBATE_PROGRAM_ID, transaction.getRebateProgramId()), null);
        }

        return RebateCalculation.builder()
                .transaction(TransactionResponse.from(transaction))
                .rebateProgram(RebateProgramResponse.from(rebateProgram))
                .amount(CommonUtil.calculateRebateAmount(transaction.getAmount(), rebateProgram.getRebatePercentage()))
                .build();
    }
}
