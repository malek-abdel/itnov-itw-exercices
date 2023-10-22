package com.itnov.bankapi.service.impl;

import com.itnov.bankapi.domain.Account;
import com.itnov.bankapi.domain.Operation;
import com.itnov.bankapi.repository.AccountRepository;
import com.itnov.bankapi.repository.OperationRepository;
import com.itnov.bankapi.repository.dto.AccountDto;
import com.itnov.bankapi.repository.dto.OperationDto;
import com.itnov.bankapi.service.OperationService;
import com.itnov.bankapi.service.exception.AccountNotFoundException;
import com.itnov.bankapi.service.exception.InvalidOperationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;

    private final AccountRepository accountRepository;

    public OperationServiceImpl(final OperationRepository operationRepository,
                                final AccountRepository accountRepository) {
        this.operationRepository = operationRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Operation createOperation(Operation operation) throws AccountNotFoundException, InvalidOperationException {
        AccountDto accountSrc = accountRepository.findById(operation.getAccountSrc().getIban())
                .orElseThrow(() -> new AccountNotFoundException(operation.getAccountSrc().getIban()));

        Operation.OperationBuilder operationBuilder = operation.toBuilder()
                .accountSrc(Account.builder()
                        .iban(accountSrc.getIban())
                        .balance(accountSrc.getBalance())
                        .overdraftLimit(accountSrc.getOverdraftLimit())
                        .build());
        OperationDto.OperationDtoBuilder operationBuilderToSave = OperationDto.builder()
                .accountSrc(accountSrc);

        if (operation.getAccountDst().getIban() != null) {
            AccountDto accountDst = accountRepository.findById(operation.getAccountDst().getIban())
                    .orElseThrow(() -> new AccountNotFoundException(operation.getAccountDst().getIban()));
            operationBuilder.accountDst(Account.builder()
                    .iban(accountDst.getIban())
                    .balance(accountDst.getBalance())
                    .overdraftLimit(accountDst.getOverdraftLimit())
                    .build());

            operationBuilderToSave.accountDst(accountDst);
        }

        Operation filledOperation = operationBuilder.build();

        if (!filledOperation.isOperationValid()) {
            throw new InvalidOperationException(filledOperation.getType());
        }

        OperationDto operationToSave = operationBuilderToSave
                .type(operation.getType().name())
                .date(LocalDateTime.now())
                .amount(operation.getAmount())
                .build();

        OperationDto savedOperation = operationRepository.save(operationToSave);

        return filledOperation.toBuilder().id(savedOperation.getId()).build();

    }
}
