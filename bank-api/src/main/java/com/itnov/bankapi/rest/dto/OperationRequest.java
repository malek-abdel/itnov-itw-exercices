package com.itnov.bankapi.rest.dto;

import com.itnov.bankapi.domain.OperationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OperationRequest {
    private OperationType type;
    private BigDecimal amount;
    private String ibanDest;
}
