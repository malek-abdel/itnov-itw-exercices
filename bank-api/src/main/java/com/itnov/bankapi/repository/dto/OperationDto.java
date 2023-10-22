package com.itnov.bankapi.repository.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "client")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private BigDecimal amount;


    private LocalDateTime date;

    @ManyToOne
    private AccountDto accountSrc;
    @ManyToOne
    private AccountDto accountDst;
}
