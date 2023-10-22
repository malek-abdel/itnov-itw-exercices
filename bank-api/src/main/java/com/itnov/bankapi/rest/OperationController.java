package com.itnov.bankapi.rest;

import com.itnov.bankapi.rest.dto.OperationListResponse;
import com.itnov.bankapi.service.OperationsHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("operations")
public class OperationController {

    private final OperationsHistoryService operationsHistoryService;

    @GetMapping("")
    public ResponseEntity<OperationListResponse> listOperations(@RequestParam String clientEmail,
                                                      @RequestParam(defaultValue = "10", required = false) int pageSize,
                                                      @RequestParam(defaultValue = "1", required = false) int pageNumber) {
        return ResponseEntity.ok(operationsHistoryService.getHistory(clientEmail, pageNumber, pageSize));
    }

}
