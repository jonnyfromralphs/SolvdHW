package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.account.Account;
import org.example.account.CheckingAccount;
import org.example.account.CreditLineAccount;
import org.example.account.SavingsAccount;
import org.example.bank.Bank;
import org.example.bank.Customer;
import org.example.employee.Employee;
import org.example.exceptions.InsufficientFundsException;
import org.example.utils.Currency;
import org.example.utils.CurrencyConverter;
import org.example.utils.Lambdas;
import org.example.utils.LinkedList;

import java.time.LocalDate;
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

        Bank chaseBank = new Bank("Chase Bank", "523 W Adams St", customers, Currency.USD);

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
        chaseBank.setEmployees(new ArrayList<>());
        chaseBank.loadEmployeeDatabase("thisFileDoesNotExist");
        System.out.println();

        chaseBank.loadEmployeeDatabase("employees");
        chaseBank.listEmployees();

        System.out.println("Predicate lambda test");
        ArrayList<Employee> filterTest = chaseBank.getEmployeesWithFilter((Employee employee) -> employee.getName().startsWith("H"));
        System.out.println(filterTest);
        System.out.println();

        System.out.println("Function lambda test");
        ArrayList<String> transformTest = chaseBank.transformEmployees((Employee employee) -> "Lambda practice: " + employee.toString());
        System.out.println(transformTest);
        System.out.println();

        System.out.println("Consumer lambda test");
        chaseBank.customGreetingWithConsumer(employee -> System.out.println("Thank you for working at Chase Bank " + employee.getName()));
        System.out.println();

        System.out.println("Supplier lambda test");
        System.out.println(Lambdas.generateElements(5, () -> LocalDate.now()));
        System.out.println();

        System.out.println("BiFunction lamba test");
        String result = Lambdas.performOperation("Hello", " World", (a, b) -> a + b);
        System.out.println(result);
        System.out.println();

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addNode(6);
        linkedList.addNode(3);
        linkedList.addNode(1);
        System.out.println(linkedList);
        linkedList.removeNode(1);
        System.out.println(linkedList);
    }
}
