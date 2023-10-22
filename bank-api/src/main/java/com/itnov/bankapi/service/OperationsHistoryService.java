package com.itnov.bankapi.service;

import com.itnov.bankapi.rest.dto.OperationListResponse;

public interface OperationsHistoryService {
    OperationListResponse getHistory(String clientEmail, int pageNumber, int pageSize);
}
