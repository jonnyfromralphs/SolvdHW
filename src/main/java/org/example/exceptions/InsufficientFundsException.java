package org.example.exceptions;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException() {
        super("Your transaction has been denied. You have insufficient funds.");
    }
}
