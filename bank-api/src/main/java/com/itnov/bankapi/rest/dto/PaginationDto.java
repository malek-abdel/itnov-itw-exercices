package com.itnov.bankapi.rest.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class PaginationDto {

    private int numberOfPages;
    private long totalCount;
    private int currentPage;
    private int pageSize;

}
