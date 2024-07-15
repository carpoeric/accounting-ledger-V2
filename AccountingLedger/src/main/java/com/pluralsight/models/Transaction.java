package com.pluralsight.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Transaction {
    @Id
    private int id;
    private int customerId;
    private BigDecimal amount;
    private LocalDateTime transactionDate;

    // Constructors

}
