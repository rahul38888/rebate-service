package com.speedybrand.rebate.utils;

import com.speedybrand.rebate.pojo.RebateProgram;
import com.speedybrand.rebate.pojo.Transaction;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;
import java.util.UUID;

public class CommonUtil {

    private CommonUtil() {
    }

    private static final ZoneId zoneId = TimeZone.getDefault().toZoneId();

    public static LocalDateTime getLocalDateTime(final Long epochMillis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMillis), zoneId);
    }

    public static Long getEpochMillis(final LocalDateTime time) {
        return time.atZone(zoneId).toInstant().toEpochMilli();
    }

    public static Double calculateRebateAmount(final Double transactionAmount, final Double rebatePercentage) {
        return transactionAmount * rebatePercentage;
    }

    public static void enrichTransaction(final Transaction transaction) {
        transaction.setRecordedAt(LocalDateTime.now());
    }

    public static void enrichRebateProgram(final RebateProgram rebateProgram) {
        rebateProgram.setId(IdGenerator.generateRebateProgramId());
        rebateProgram.setCreatedAt(LocalDateTime.now());
    }
}
