package org.example.account;

import org.example.interfaces.Interestable;

public class CreditLineAccount extends Account implements Interestable {
    private final double INTEREST_RATE;
    private double creditLimit;

    public CreditLineAccount(String accountNumber, double balance, double interestRate, double creditLimit) {
        super(accountNumber, balance);
        this.INTEREST_RATE = interestRate;
        this.creditLimit = creditLimit;
    }

    public double getInterestRate() {
        return INTEREST_RATE;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void applyInterest() {
        double interestPercent = INTEREST_RATE / 100;
        double newAmount = this.balance + this.balance * interestPercent;
        super.setBalance(newAmount);
    }

    @Override
    public void deposit(double depositAmount) {
        if (depositAmount > this.balance) {
            this.balance = 0;
        } else {
            this.balance -= depositAmount;
        }

        System.out.println("Your remaining balance on your credit card is " + this.balance);
    }

    @Override
    public void withdraw(double withdrawAmount) {
        if (this.balance + withdrawAmount > creditLimit) {
            System.out.println("Your transaction has been denied. You have exceeded your credit limit.");
        } else {
            this.balance += withdrawAmount;
            System.out.println("Your new balance on your credit card is " + this.balance);
        }
    }
}

