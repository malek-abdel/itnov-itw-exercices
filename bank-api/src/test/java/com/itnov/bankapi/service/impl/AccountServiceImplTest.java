package com.itnov.bankapi.service.impl;

import com.itnov.bankapi.domain.Account;
import com.itnov.bankapi.repository.AccountRepository;
import com.itnov.bankapi.repository.dto.AccountDto;
import com.itnov.bankapi.rest.dto.ListAccountResponse;
import com.itnov.bankapi.rest.dto.PaginationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;


    @Test
    void getAllByClient_shouldReturnPaginatedAccounts() {
        String email = "toto@tata.fr";
        Page<AccountDto> accountsFromDb = new PageImpl<>(List.of(new AccountDto(), new AccountDto(), new AccountDto()),
                PageRequest.of(0, 5), 3);
        when(accountRepository.getAccountDtosByClientEmail(email, PageRequest.of(0, 5)))
                .thenReturn(accountsFromDb);
        ListAccountResponse result = accountService.getAllByClient(email, 5, 1);
        ListAccountResponse expected = new ListAccountResponse(new PaginationDto(1, 3L, 1, 5),
                List.of(new Account(), new Account(), new Account()));

        assertThat(result).isEqualTo(expected);

    }

    @Test
    void getAllByClient_shouldReturnEmptyAccountList() {
        String email = "notAClient@tata.fr";
        Page<AccountDto> accountsFromDb = new PageImpl<>(List.of(),
                PageRequest.of(0, 5), 0);
        when(accountRepository.getAccountDtosByClientEmail(email, PageRequest.of(0, 5)))
                .thenReturn(accountsFromDb);
        ListAccountResponse result = accountService.getAllByClient(email, 5, 1);
        ListAccountResponse expected = new ListAccountResponse(new PaginationDto(0, 0L, 1, 5),
                List.of());

        assertThat(result).isEqualTo(expected);

    }

}