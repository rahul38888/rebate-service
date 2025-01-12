package com.speedybrand.rebate.models.responses;

import com.speedybrand.rebate.pojo.Transaction;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class RebateCalculation implements Serializable {

    private TransactionResponse transaction;
    private RebateProgramResponse rebateProgram;
    private Double amount;

}
