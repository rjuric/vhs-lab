package com.rjuric.vhs_lab.util.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RentalBill {
     private long daysOverdue;
     private int feePerDayInUSD = 5;

     public long getTotalFeesInUsd() {
         return daysOverdue * feePerDayInUSD;
     }
}
