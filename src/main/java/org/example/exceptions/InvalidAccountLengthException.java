package org.example.exceptions;

public class InvalidAccountLengthException extends Exception {
    public InvalidAccountLengthException() {
        super("Account number must be at least 8 digits long.");
    }
}
