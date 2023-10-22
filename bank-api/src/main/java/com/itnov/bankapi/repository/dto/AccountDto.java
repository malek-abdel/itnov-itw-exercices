package com.itnov.bankapi.repository.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
@Getter
public class AccountDto {

    @Id
    private String iban;
    private BigDecimal balance;
    private BigDecimal overdraftLimit;
    @ManyToOne
    private ClientDto client;
}
