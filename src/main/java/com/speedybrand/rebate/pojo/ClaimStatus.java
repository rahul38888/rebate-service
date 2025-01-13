package com.speedybrand.rebate.pojo;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum ClaimStatus {
    PENDING(0, "pending"),
    APPROVED(1, "approved"),
    REJECTED(2, "rejected");

    private final Integer id;
    private final String name;

    ClaimStatus(final Integer id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static ClaimStatus from(final Integer id) {
        for (ClaimStatus status : ClaimStatus.values()) {
            if(Objects.equals(status.id, id)) {
                return status;
            }
        }
        return null;
    }

    public static ClaimStatus from(final String name) {
        for (ClaimStatus status : ClaimStatus.values()) {
            if(Objects.equals(status.name, name)) {
                return status;
            }
        }
        return null;
    }
}
