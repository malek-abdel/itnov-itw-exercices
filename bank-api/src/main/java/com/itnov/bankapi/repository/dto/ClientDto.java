package com.itnov.bankapi.repository.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    @Id
    private String email;
    private String name;
}
