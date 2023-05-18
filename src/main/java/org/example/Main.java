package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entities.account.Account;
import org.example.entities.account.CheckingAccount;
import org.example.entities.account.CreditLineAccount;
import org.example.entities.account.SavingsAccount;
import org.example.entities.bank.Bank;
import org.example.entities.bank.Customer;
import org.example.entities.employee.Employee;
import org.example.exceptions.InsufficientFundsException;
import org.example.services.BankService;
import org.example.services.Currency;
import org.example.utils.CurrencyConverter;
import org.example.utils.Lambdas;
import org.example.utils.LinkedList;

import java.lang.reflect.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Main {

    public static Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws InsufficientFundsException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
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

        BankService bankService = new BankService();
        ArrayList<Employee> employees = chaseBank.getEmployees();

        System.out.println("Predicate lambda test");
        ArrayList<Employee> filterTest = bankService.getEmployeesWithFilter((Employee employee) -> employee.getName().startsWith("H"), employees);
        System.out.println(filterTest);
        System.out.println();

        System.out.println("Function lambda test");
        ArrayList<String> transformTest = bankService.transformEmployees((Employee employee) -> "Lambda practice: " + employee.toString(), employees);
        System.out.println(transformTest);
        System.out.println();

        System.out.println("Consumer lambda test");
        bankService.customGreetingWithConsumer(employee -> System.out.println("Thank you for working at Chase Bank " + employee.getName()), employees);
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

        System.out.println("\nStreams and collections\n");

        List<Employee> henryFiltered = employees.stream().filter(e -> e.getName().startsWith("Henry")).toList();
        System.out.println(henryFiltered);
        List<String> namesMapped = employees.stream().map(Employee::getName).toList();
        System.out.println(namesMapped);
        List<Set<Account>> allAccounts = customers.stream().map(Customer::getCustomerAccounts).toList();
        List<Double> allBalances = allAccounts.stream().flatMap(accounts -> accounts.stream().map(Account::getBalance)).toList();
        double totalBalance = allBalances.stream().reduce(0.0, Double::sum);
        System.out.println(totalBalance);

        Optional<Customer> findCustomer = customers.stream().filter(customer -> customer.getName().startsWith("J")).findAny();
        if (findCustomer.isPresent()) {
            System.out.println("Found: " + findCustomer.get());
        } else {
            System.out.println("No such element was found");
        }

        Optional<Customer> findCustomerTest = customers.stream().filter(customer -> customer.getName().startsWith("Z")).findAny();
        if (findCustomerTest.isPresent()) {
            System.out.println("Found: " + findCustomerTest.get());
        } else {
            System.out.println("No such element was found");
        }

        System.out.println("\nReflections\n");

        Class<?> bank = Bank.class;

        Field[] fields = bank.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("Field name: " + field.getName());
            System.out.println("Field type: " + field.getType());
            System.out.println("Field modifiers: " + Modifier.toString(field.getModifiers()));
            System.out.println();
        }

        Constructor<?>[] constructors = bank.getDeclaredConstructors();

        for (Constructor<?> constructor : constructors) {
            System.out.println("Constructor name: " + constructor.getName());

            Parameter[] parameters = constructor.getParameters();

            System.out.println("Parameters: ");
            for (Parameter parameter : parameters) {
                System.out.println(parameter.getType() + " " + parameter.getName());
            }
            System.out.println();
        }

        Constructor<?> constructor = bank.getDeclaredConstructor(String.class, String.class, ArrayList.class, Currency.class);
        Bank bankWithReflection = (Bank) constructor.newInstance("New Bank with reflection", "123 Testing", null, null);
        Method bankMethod = bank.getDeclaredMethod("toString");
        System.out.println(bankMethod.invoke(bankWithReflection));

    }
}
