package com.itnov.bankapi.service.exception;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String iban) {
        super("Account with iban ".concat(iban).concat(" couldn't be found"));
    }
}
