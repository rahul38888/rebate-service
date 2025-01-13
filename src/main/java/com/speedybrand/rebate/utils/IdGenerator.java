package com.speedybrand.rebate.utils;

import java.util.UUID;

public class IdGenerator {

    private static final String REBATE_PROGRAM_ID_FORMAT = "arn:reb:%s";
    private static final String TRANSACTION_ID_FORMAT = "arn:trx:%s";
    private static final String CLAIM_ID_FORMAT = "arn:clm:%s";

    private IdGenerator() {}

    public static String generateRebateProgramId() {
        return String.format(REBATE_PROGRAM_ID_FORMAT, UUID.randomUUID());
    }

    public static String generateTransactionId() {
        return String.format(TRANSACTION_ID_FORMAT, UUID.randomUUID());
    }

    public static String getClaimId(final String transactionId) {
        return String.format(CLAIM_ID_FORMAT, transactionId);
    }
}
