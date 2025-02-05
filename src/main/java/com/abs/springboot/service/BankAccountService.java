package com.abs.springboot.service;

import com.abs.springboot.dto.BankAccountDto;
import com.abs.springboot.entity.BankAccount;

import java.util.List;
import java.util.Optional;

public interface BankAccountService {
    BankAccountDto createAccount(BankAccountDto bankAccountDto);

    List<BankAccountDto> getAllAccounts();

    BankAccountDto getAccountById(Long id);

    BankAccountDto depositAmount(Long id, double amount);

    BankAccountDto withdrawAmount(Long id, double amount);

    BankAccountDto updateAccountById(Long id,BankAccountDto bankAccountDto);

    void deleteAccount(Long id);

    String checkBalance(Long id);
}
