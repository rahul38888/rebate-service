package com.speedybrand.rebate.service.impls;

import com.mongodb.client.model.Filters;
import com.speedybrand.rebate.exceptions.EntityNotFoundException;
import com.speedybrand.rebate.exceptions.InputValidationException;
import com.speedybrand.rebate.exceptions.UpdateFailureException;
import com.speedybrand.rebate.pojo.Claim;
import com.speedybrand.rebate.pojo.ClaimStatus;
import com.speedybrand.rebate.pojo.RebateProgram;
import com.speedybrand.rebate.pojo.Transaction;
import com.speedybrand.rebate.repo.IClaimRepo;
import com.speedybrand.rebate.repo.IRebateProgramRepo;
import com.speedybrand.rebate.repo.ITransactionRepo;
import com.speedybrand.rebate.service.IClaimService;
import com.speedybrand.rebate.utils.CommonUtil;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@Service
public class ClaimService implements IClaimService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClaimService.class);

    private final IClaimRepo claimRepo;
    private final ITransactionRepo transactionRepo;
    private final IRebateProgramRepo rebateProgramRepo;

    public ClaimService(final IClaimRepo claimRepo, final ITransactionRepo transactionRepo,
                        final IRebateProgramRepo rebateProgramRepo) {
        this.claimRepo = claimRepo;
        this.transactionRepo = transactionRepo;
        this.rebateProgramRepo = rebateProgramRepo;
    }

    @Override
    public Claim getClaim(final String claimId) {
        final Claim claim = claimRepo.get(claimId);
        if (Objects.isNull(claim)) {
            LOGGER.error("Claim not found in database, id = {}", claimId);
            throw new EntityNotFoundException(Map.of(CommonUtil.CLAIM_ID, claimId), null);
        }
        return claim;
    }

    @Override
    public Claim createClaim(final String transactionId) {
        final Transaction transaction = transactionRepo.get(transactionId);
        if (Objects.isNull(transaction)) {
            LOGGER.error("Transaction not found in database, transactionId = {}", transactionId);
            throw new InputValidationException(Map.of(CommonUtil.TRANSACTION_ID, transactionId), null);
        }

        final RebateProgram rebateProgram = rebateProgramRepo.get(transaction.getRebateProgramId());
        if (Objects.isNull(rebateProgram)) {
            LOGGER.error("Rebate program for transaction not found, transactionId = {}, rebateProgramId = {}",
                    transactionId, transaction.getRebateProgramId());
            throw new InputValidationException(Map.of(CommonUtil.TRANSACTION_ID, transactionId,
                    CommonUtil.REBATE_PROGRAM_ID, transaction.getRebateProgramId()), null);
        }

        final Claim claim = new Claim();
        claim.setTransactionId(transactionId);
        claim.setClaimAmount(
                CommonUtil.calculateRebateAmount(transaction.getAmount(), rebateProgram.getRebatePercentage()));
        claim.setClaimDate(LocalDateTime.now());
        claim.setStatus(ClaimStatus.PENDING);

        return claimRepo.create(claim);
    }

    @Override
    public void approve(final String claimId) {
        updateStatus(claimId, ClaimStatus.PENDING, ClaimStatus.APPROVED);
    }

    @Override
    public void reject(final String claimId) {
        updateStatus(claimId, ClaimStatus.PENDING, ClaimStatus.REJECTED);
    }

    private void updateStatus(final String claimId, final ClaimStatus from, final ClaimStatus to) {
        Bson filter = Filters.and(Filters.eq(Claim.DbConstant.ID, claimId),
                Filters.eq(Claim.DbConstant.STATUS, from.getId()));
        boolean success = claimRepo.updateProperties(filter, Map.of(Claim.DbConstant.STATUS, to));
        if (!success) {
            throw new UpdateFailureException(null);
        }
    }

}
