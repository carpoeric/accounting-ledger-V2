package com.pluralsight.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Transactions {
    private int id;
    private int customerId;
    private int transactionTypeID;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
}
