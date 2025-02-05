package com.abs.springboot.controller;

import com.abs.springboot.dto.BankAccountDto;
import com.abs.springboot.entity.BankAccount;
import com.abs.springboot.service.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/accounts")
public class BankAccountController {

    private BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    // Add Bank Account
    @PostMapping
    public ResponseEntity<BankAccountDto> createAccount(@RequestBody BankAccountDto bankAccountDto){
        return new ResponseEntity<>(bankAccountService.createAccount(bankAccountDto),HttpStatus.CREATED);
    }

    // Get Account
    @GetMapping
    public ResponseEntity<List<BankAccountDto>> getAllAccounts(){
        return new ResponseEntity<>(bankAccountService.getAllAccounts(),HttpStatus.OK);
    }

    // Get Account By Id
    @GetMapping("/{id}")
    public ResponseEntity<BankAccountDto> getAccountById(@PathVariable Long id){
        return new ResponseEntity<>(bankAccountService.getAccountById(id),HttpStatus.OK);
    }

    // Deposit Amount
    @PutMapping("/{id}/deposit")
    public ResponseEntity<BankAccountDto> depositBalance(@PathVariable Long id,
                                                         @RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        return new ResponseEntity<>(bankAccountService.depositAmount(id, amount),HttpStatus.OK);
    }

    // Withdraw Amount
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<BankAccountDto> withdrawAmount(@PathVariable Long id,
                                                        @RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        return new ResponseEntity<>(bankAccountService.withdrawAmount(id,amount),HttpStatus.OK);
    }

    //Delete Account
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        bankAccountService.deleteAccount(id);
        return ResponseEntity.ok("Account is Deleted Successfully");
    }

    // Update Account
    @PutMapping("/{id}")
    public ResponseEntity<BankAccountDto> updateAccountById(@PathVariable Long id,
                                                            @RequestBody BankAccountDto bankAccountDto){
        BankAccountDto accountDto = bankAccountService.updateAccountById(id, bankAccountDto);
        return new ResponseEntity<>(accountDto,HttpStatus.OK);
    }

    // Check Balance
    @GetMapping("/{id}/balance")
    public ResponseEntity<String> checkBalance(@PathVariable("id") Long id){
        return new ResponseEntity<>(bankAccountService.checkBalance(id),HttpStatus.OK);
    }
}
