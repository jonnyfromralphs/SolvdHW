package org.example.utils;

public class CurrencyConverter
{
    public static void convertUSDtoPesos(double amount)
    {
        final double CONVERSION_RATE = 18.08;
        double amountInPesos = amount * CONVERSION_RATE;
        System.out.println("$" + amount + " in pesos is Mex$" + amountInPesos);
    }

    public static void convertUSDtoEuros(double amount)
    {
        final double CONVERSION_RATE = 0.91;
        double amountInEuros = amount * CONVERSION_RATE;
        System.out.println("$" + amount + " in euros is €" + amountInEuros);
    }

    public static void convertUSDtoYen(double amount)
    {
        final double CONVERSION_RATE = 134.09;
        double amountInYen = amount * CONVERSION_RATE;
        System.out.println("$" + amount + " in yen is ¥" + amountInYen);
    }
}
