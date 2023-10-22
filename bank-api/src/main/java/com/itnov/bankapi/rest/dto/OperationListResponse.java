package com.itnov.bankapi.rest.dto;

import com.itnov.bankapi.domain.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OperationListResponse {
    private PaginationDto page;
    private List<Operation> operations;
}
