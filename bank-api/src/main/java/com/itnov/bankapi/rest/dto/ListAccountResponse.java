package com.itnov.bankapi.rest.dto;

import com.itnov.bankapi.domain.Account;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ListAccountResponse {
    private PaginationDto page;

    private List<Account> account;
}
