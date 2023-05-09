package org.example.exceptions;

public class InvalidCSVFileException extends RuntimeException {
    public InvalidCSVFileException() {
        super("The given CSV file is missing required information");
    }

}
