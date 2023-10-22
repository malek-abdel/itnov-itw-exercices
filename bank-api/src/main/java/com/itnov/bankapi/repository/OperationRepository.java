package com.itnov.bankapi.repository;

import com.itnov.bankapi.repository.dto.OperationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<OperationDto, String> {

    Page<OperationDto> findAllByAccountSrcClientEmail(String email, Pageable pageable);

}
