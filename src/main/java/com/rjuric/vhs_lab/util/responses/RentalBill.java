package com.rjuric.vhs_lab.util.responses;

public class RentalBill {
     private long daysOverdue;

     private double totalFeeInUSD;

     private int feePerDayInUSD;

    public RentalBill(long daysOverdue, int feePerDayInUSD) {
        this.daysOverdue = daysOverdue;
        this.feePerDayInUSD = feePerDayInUSD;
        this.totalFeeInUSD = daysOverdue * feePerDayInUSD;
    }

    public RentalBill(long daysOverdue) {
        this.daysOverdue = daysOverdue;
        this.feePerDayInUSD = 5;
        this.totalFeeInUSD = daysOverdue * feePerDayInUSD;
    }

    public long getDaysOverdue() {
        return daysOverdue;
    }

    public void setDaysOverdue(long daysOverdue) {
        this.daysOverdue = daysOverdue;
    }

    public double getTotalFeeInUSD() {
        return totalFeeInUSD;
    }

    public void setTotalFeeInUSD(double totalFeeInUSD) {
        this.totalFeeInUSD = totalFeeInUSD;
    }

    public int getFeePerDayInUSD() {
        return feePerDayInUSD;
    }

    public void setFeePerDayInUSD(int feePerDayInUSD) {
        this.feePerDayInUSD = feePerDayInUSD;
    }
}
