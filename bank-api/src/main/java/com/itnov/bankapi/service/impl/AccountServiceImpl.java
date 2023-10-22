package com.itnov.bankapi.service.impl;

import com.itnov.bankapi.domain.Account;
import com.itnov.bankapi.repository.AccountRepository;
import com.itnov.bankapi.repository.dto.AccountDto;
import com.itnov.bankapi.rest.dto.ListAccountResponse;
import com.itnov.bankapi.rest.dto.PaginationDto;
import com.itnov.bankapi.service.AccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public ListAccountResponse getAllByClient(String clientEmail, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<AccountDto> accountsDto = accountRepository.getAccountDtosByClientEmail(clientEmail, pageable);

        PaginationDto page = new PaginationDto(accountsDto.getTotalPages(), accountsDto.getTotalElements(),
                pageNumber, accountsDto.getSize());

        List<Account> accounts = accountsDto.stream()
                .map(accountDto -> new Account(accountDto.getIban(), accountDto.getBalance(),
                        accountDto.getOverdraftLimit()))
                .collect(Collectors.toList());

        return new ListAccountResponse(page, accounts);
    }
}
