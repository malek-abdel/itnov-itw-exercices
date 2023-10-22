package com.itnov.bankapi.service.exception;

import com.itnov.bankapi.domain.OperationType;

public class InvalidOperationException extends Exception {
    public InvalidOperationException(OperationType operationType) {
        super(operationType.name().concat(" has been refused. Please check your balance"));
    }
}
