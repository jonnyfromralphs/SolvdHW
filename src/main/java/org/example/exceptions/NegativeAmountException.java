package org.example.exceptions;

public class NegativeAmountException extends Exception {
    public NegativeAmountException() {
        super("The bank only accepts values greater than or equal to 0.");
    }

}
