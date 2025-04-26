package com.abs.springboot.service.impl;

import com.abs.springboot.dto.BankAccountDto;
import com.abs.springboot.entity.BankAccount;
import com.abs.springboot.repository.BankAccountRepository;
import com.abs.springboot.service.BankAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private BankAccountRepository bankAccountRepository;
    private ModelMapper mapper;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository,
                                  ModelMapper mapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.mapper=mapper;
    }

    @Override
    public BankAccountDto createAccount(BankAccountDto bankAccountDto) {

        BankAccount bankAccount = mapToEntity(bankAccountDto);
        BankAccount newBankAccount = bankAccountRepository.save(bankAccount);
        return mapToDto(newBankAccount);
    }

    @Override
    public List<BankAccountDto> getAllAccounts() {

        List<BankAccount> bankAccounts =  bankAccountRepository.findAll();
        return bankAccounts.stream().map((account)
                -> mapToDto(account)).collect(Collectors.toList());

//        List<BankAccountDto> accounts = new ArrayList<>();
//        for (BankAccount b : bankAccounts){
//            BankAccountDto accountDto = mapToDto(b);
//            accounts.add(accountDto);
//        }
        //        return accounts;

    }

    @Override
    public BankAccountDto getAccountById(Long id) {

        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(()
                ->new RuntimeException("Account does not exists"));
        return mapToDto(bankAccount);
    }

    @Override
    public BankAccountDto depositAmount(Long id, double amount) {

        BankAccount bankAccount = bankAccountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Accounts does not exits"));

        double totalBalance = bankAccount.getBalance()+ amount ;
        bankAccount.setBalance(totalBalance);
        BankAccount bankAccount1 = bankAccountRepository.save(bankAccount);
        return mapToDto(bankAccount1);
    }

    @Override
    public BankAccountDto withdrawAmount(Long id, double amount) {

        BankAccount bankAccount = bankAccountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Accounts does not exits"));

        if (bankAccount.getBalance()<amount){
            throw new RuntimeException("Insufficient Amount");
        }

        double totalBalance = bankAccount.getBalance()-amount;
        bankAccount.setBalance(totalBalance);
        BankAccount bankAccount1 = bankAccountRepository.save(bankAccount);

        return mapToDto(bankAccount1);
    }

    @Override
    public BankAccountDto updateAccountById(Long id, BankAccountDto bankAccountDto) {

        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Account is not available"));

        bankAccount.setAccountNo(bankAccountDto.getAccountNo());
        bankAccount.setName(bankAccountDto.getName());
        bankAccount.setIfscCode(bankAccountDto.getIfscCode());
        bankAccount.setBalance(bankAccountDto.getBalance());

        BankAccount updatedBankAccount = bankAccountRepository.save(bankAccount);
        return mapToDto(updatedBankAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        BankAccount bankAccount = bankAccountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Accounts does not exist"));

        bankAccountRepository.deleteById(id);
    }

    @Override
    public String checkBalance(Long id) {

        BankAccount bankAccount = bankAccountRepository.findById(id).orElseThrow(()
                                    -> new RuntimeException("Account doesn't exist!"));
        double balance = bankAccount.getBalance();
        return "Balance is : "+balance;
    }

    // Entity to DTO mapping
    private BankAccountDto mapToDto(BankAccount bankAccount){
        BankAccountDto bankAccountDto = mapper.map(bankAccount,BankAccountDto.class);
        return bankAccountDto;
    }

    // DTO to Entity mapping
    private BankAccount mapToEntity(BankAccountDto bankAccountDto){
        BankAccount bankAccount = mapper.map(bankAccountDto,BankAccount.class);
        return bankAccount;
    }
}
