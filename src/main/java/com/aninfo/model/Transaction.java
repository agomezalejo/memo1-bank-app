package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaction {

    public enum TransactionType {
        WITHDRAW, DEPOSIT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private Double sum;

    public Transaction() {}

    public Transaction(TransactionType transactionType, Double sum) {
        this.transactionType = transactionType;
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
