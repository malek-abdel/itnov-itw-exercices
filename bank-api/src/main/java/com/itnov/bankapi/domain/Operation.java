package com.itnov.bankapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder(toBuilder = true)
public class Operation {

    private Long id;

    private OperationType type;

    private BigDecimal amount;

    private LocalDateTime date;
    private Account accountSrc;
    private Account accountDst;

    public boolean isOperationValid() {
        if (List.of(OperationType.WITHDRAW, OperationType.TRANSFER).contains(type)) {
            return accountSrc.getOverdraftLimit().compareTo(accountSrc.getBalance().subtract(amount)) < 0;
        }
        return true;
    }
}
