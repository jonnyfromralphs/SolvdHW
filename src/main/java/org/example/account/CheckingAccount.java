package org.example.account;

import org.example.Main;
import org.example.exceptions.InsufficientFundsException;
import org.example.exceptions.NegativeAmountException;

public class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, double balance, double overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void deposit(double depositAmount) {
        try {
            if (depositAmount < 0) {
                throw new NegativeAmountException();
            }
            this.balance += depositAmount;
            System.out.println("You have deposited $" + depositAmount + ". Your new balance is $" + this.balance + ".");
        } catch (NegativeAmountException e) {
            Main.logger.error(e.getMessage());
        }

    }

    @Override
    public void withdraw(double withdrawAmount) {
        try {
            if (withdrawAmount < 0) {
                throw new NegativeAmountException();
            } else if (this.balance - withdrawAmount >= -this.overdraftLimit) {
                this.balance -= withdrawAmount;
                System.out.println("You have withdrawn $" + withdrawAmount + ". Your new balance is $" + this.balance + ".");
            } else {
                throw new InsufficientFundsException();
            }
        } catch (Exception e) {
            Main.logger.error(e.getMessage());
        }
    }
}