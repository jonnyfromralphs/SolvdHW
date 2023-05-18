package org.example.entities.account;

import org.example.services.interfaces.Interestable;

public class SavingsAccount extends Account implements Interestable {
    private final double INTEREST_RATE;

    public SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.INTEREST_RATE = interestRate;
    }

    public double getInterestRate() {
        return INTEREST_RATE;
    }

    public void applyInterest() {
        double interestPercent = INTEREST_RATE / 100;
        double newAmount = super.getBalance() + super.getBalance() * interestPercent;
        super.setBalance(newAmount);
    }

    @Override
    public void deposit(double depositAmount) {
        this.balance += depositAmount;
        System.out.println("You have deposited $" + depositAmount + ". Your new balance is $" + this.balance + ".");
    }

    @Override
    public void withdraw(double withdrawAmount) {
        if (this.balance - withdrawAmount >= 0) {
            this.balance -= withdrawAmount;
            System.out.println("You have withdrawn $" + withdrawAmount + ". Your new balance is $" + this.balance + ".");
        } else {
            System.out.println("Transaction has been denied.");
        }
    }
}

