package com.itnov.bankapi.rest;

import com.itnov.bankapi.domain.Account;
import com.itnov.bankapi.domain.Operation;
import com.itnov.bankapi.rest.dto.ListAccountResponse;
import com.itnov.bankapi.rest.dto.OperationRequest;
import com.itnov.bankapi.service.AccountService;
import com.itnov.bankapi.service.OperationService;
import com.itnov.bankapi.service.exception.AccountNotFoundException;
import com.itnov.bankapi.service.exception.InvalidOperationException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("accounts")
public class AccountController {

    private final AccountService accountService;
    private final OperationService operationService;

    @GetMapping("")
    public ResponseEntity<ListAccountResponse> list(@RequestParam String clientEmail,
                                                    @RequestParam(defaultValue = "10", required = false) int pageSize,
                                                    @RequestParam(defaultValue = "1", required = false) int pageNumber) {
        return ResponseEntity.ok(accountService.getAllByClient(clientEmail, pageSize, pageNumber));
    }

    @PostMapping("{iban}/operation")
    public ResponseEntity<Operation> createOperation(@PathVariable String iban,
                                                     @RequestBody OperationRequest operationRequest) throws InvalidOperationException, AccountNotFoundException {
        final Operation operation = Operation.builder()
                .accountSrc(Account.builder().iban(iban).build())
                .type(operationRequest.getType())
                .amount(operationRequest.getAmount())
                .build();
        Operation savedOperation = operationService.createOperation(operation);
        return ResponseEntity.created(URI.create("/operations/".concat(String.valueOf(savedOperation.getId()))))
                .body(savedOperation);

    }

}
