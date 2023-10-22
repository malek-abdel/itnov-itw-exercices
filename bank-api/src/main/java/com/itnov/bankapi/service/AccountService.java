package com.itnov.bankapi.service;

import com.itnov.bankapi.rest.dto.ListAccountResponse;

public interface AccountService {
    ListAccountResponse getAllByClient(String clientEmail, int pageSize, int pageNumber);
}
