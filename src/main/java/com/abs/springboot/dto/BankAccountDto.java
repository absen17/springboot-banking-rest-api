package com.abs.springboot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankAccountDto {
        private Long id;
        private String name;
        private Long accountNo;
        private String ifscCode;
        private double balance;
}
