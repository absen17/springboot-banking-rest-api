package com.abs.springboot.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long accountNo;
    private String ifscCode;
    private double balance;
}
