package org.example.services;

public enum Currency {
    USD("United States Dollar", "$", 2),
    EUR("Euro", "€", 2),
    GBP("British Pound Sterling", "£", 2),
    JPY("Japanese Yen", "¥", 0),
    CAD("Canadian Dollar", "C$", 2);

    private final String fullName;
    private final String symbol;
    private final int decimalPlaces;

    Currency(String fullName, String symbol, int decimalPlaces) {
        this.fullName = fullName;
        this.symbol = symbol;
        this.decimalPlaces = decimalPlaces;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getDecimalPlaces() {
        return decimalPlaces;
    }

    public String formatAmount(double amount) {
        String formatString = "%,." + decimalPlaces + "f";
        return symbol + String.format(formatString, amount);
    }


}