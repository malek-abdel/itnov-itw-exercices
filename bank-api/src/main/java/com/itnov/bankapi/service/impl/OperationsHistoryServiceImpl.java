package com.itnov.bankapi.service.impl;

import com.itnov.bankapi.domain.Account;
import com.itnov.bankapi.domain.Operation;
import com.itnov.bankapi.domain.OperationType;
import com.itnov.bankapi.repository.OperationRepository;
import com.itnov.bankapi.repository.dto.AccountDto;
import com.itnov.bankapi.repository.dto.OperationDto;
import com.itnov.bankapi.rest.dto.OperationListResponse;
import com.itnov.bankapi.rest.dto.PaginationDto;
import com.itnov.bankapi.service.OperationsHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationsHistoryServiceImpl implements OperationsHistoryService {

    private final OperationRepository operationRepository;

    public OperationsHistoryServiceImpl(final OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public OperationListResponse getHistory(String clientEmail, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<OperationDto> operationsDto = this.operationRepository.findAllByAccountSrcClientEmail(clientEmail, pageable);

        PaginationDto page = new PaginationDto(operationsDto.getTotalPages(), operationsDto.getTotalElements(),
                operationsDto.getNumber(), operationsDto.getSize());


        List<Operation> operations = operationsDto.stream()
                .map(operationDto -> {
                    AccountDto accountDtoSrc = operationDto.getAccountSrc();
                    Account accountSrc = new Account(accountDtoSrc.getIban(), accountDtoSrc.getBalance(), accountDtoSrc.getOverdraftLimit());
                    AccountDto accountDtoDst = operationDto.getAccountSrc();
                    Account accountDst = new Account(accountDtoDst.getIban(), accountDtoDst.getBalance(), accountDtoDst.getOverdraftLimit());

                    return new Operation(operationDto.getId(), OperationType.valueOf(operationDto.getType()),
                            operationDto.getAmount(), operationDto.getDate(), accountSrc, accountDst);
                })
                .collect(Collectors.toList());

        return new OperationListResponse(page, operations);
    }
}
