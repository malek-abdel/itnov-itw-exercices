package com.itnov.bankapi.service;

import com.itnov.bankapi.domain.Operation;
import com.itnov.bankapi.service.exception.AccountNotFoundException;
import com.itnov.bankapi.service.exception.InvalidOperationException;

public interface OperationService {
    Operation createOperation(Operation operation) throws AccountNotFoundException, InvalidOperationException;
}
