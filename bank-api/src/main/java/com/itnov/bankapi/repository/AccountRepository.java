package com.itnov.bankapi.repository;

import com.itnov.bankapi.repository.dto.AccountDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<AccountDto, String> {

    Page<AccountDto> getAccountDtosByClientEmail(String email, Pageable page);

}
