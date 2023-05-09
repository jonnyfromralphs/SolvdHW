package org.example.exceptions;

public class InvalidAccountFormatException extends Exception {
    public InvalidAccountFormatException() {
        super("Account number must a string of numeric values.");
    }
}