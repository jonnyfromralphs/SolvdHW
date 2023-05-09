package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.account.Account;
import org.example.account.CheckingAccount;
import org.example.account.CreditLineAccount;
import org.example.account.SavingsAccount;
import org.example.bank.Bank;
import org.example.bank.Customer;
import org.example.exceptions.InsufficientFundsException;
import org.example.utils.CurrencyConverter;
import org.example.utils.LinkedList;

import java.util.ArrayList;

public class Main {

    public static Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws InsufficientFundsException {
        ArrayList<Customer> customers = new ArrayList<>();

        Account checkingAccount = new CheckingAccount("12983190", 10500.50, 50);
        Account savingsAccount = new SavingsAccount("127341809324", 50000, 3.2);
        Account creditLine = new CreditLineAccount("11928734", 342.10, 20.5, 5000);

        Customer john = new Customer("John");
        john.addAccount(checkingAccount);
        Customer sarah = new Customer("Sarah");
        sarah.addAccount(savingsAccount);
        Customer danny = new Customer("Danny");
        danny.addAccount(creditLine);

        customers.add(john);
        customers.add(sarah);
        customers.add(danny);

        Bank chaseBank = new Bank("Chase Bank", "523 W Adams St", customers);

        System.out.println("*********************");
        System.out.println("Week 3 and 4 Homework");
        System.out.println("*********************\n");

        CurrencyConverter.convertUSDtoPesos(1000);
        CurrencyConverter.convertUSDtoEuros(1000);
        CurrencyConverter.convertUSDtoYen(1000);
        System.out.println();

        for (Customer customer : customers) {
            System.out.println(customer);
            customer.listAccounts();
            System.out.println();
        }

        checkingAccount.withdraw(20000);
        checkingAccount.withdraw(-100);
        checkingAccount.deposit(-500);
        chaseBank.loadEmployeeDatabase("employeesTest");
        chaseBank.loadEmployeeDatabase("thisFileDoesNotExist");
        System.out.println();

        chaseBank.loadEmployeeDatabase("employees");
        chaseBank.listEmployees();

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addNode(6);
        linkedList.addNode(3);
        linkedList.addNode(1);
        System.out.println(linkedList);
        linkedList.removeNode(1);
        System.out.println(linkedList);
    }
}
