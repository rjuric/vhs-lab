package com.rjuric.vhs_lab.util.responses;

public record RentalBill(long daysOverdue, int feePerDayInUSD) {
    public static final int FEE_PER_DAY_IN_USD = 5;

    public long getTotalFeesInUsd() {
        return daysOverdue * feePerDayInUSD;
    }
}
