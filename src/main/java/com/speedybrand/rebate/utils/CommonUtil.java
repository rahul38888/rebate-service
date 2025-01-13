package com.speedybrand.rebate.utils;

import com.speedybrand.rebate.pojo.RebateProgram;
import com.speedybrand.rebate.pojo.Transaction;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class CommonUtil {

    public static final String ID = "_id";
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    public static final String REBATE_PROGRAM_ID = "rebateProgramId";
    public static final String TRANSACTION_ID = "transactionId";

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

    public static LocalDateTime parseLocalDateTime(final String stringTime) {
        return LocalDateTime.parse(stringTime, TIME_FORMATTER);
    }

    public static void main(String[] args) {
        System.out.println(parseLocalDateTime("2024-01-13T00:04:01.546+00:00"));
    }
}
