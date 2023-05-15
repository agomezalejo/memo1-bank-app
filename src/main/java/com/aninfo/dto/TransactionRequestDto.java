package com.aninfo.dto;


import com.aninfo.model.Transaction;

public class TransactionRequestDto {
    private double sum;
    private Transaction.TransactionType type;

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Transaction.TransactionType getType() {
        return type;
    }

    public void setType(Transaction.TransactionType type) {
        this.type = type;
    }
}
