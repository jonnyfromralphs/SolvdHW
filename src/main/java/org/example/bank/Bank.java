package org.example.bank;

import org.example.Main;
import org.example.employee.Employee;
import org.example.employee.Manager;
import org.example.employee.Teller;
import org.example.exceptions.InvalidCSVFileException;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Bank {
    private String bankName;
    private String address;
    private ArrayList<Employee> employees;
    private ArrayList<Customer> customers;

    public Bank(String bankName, String address, ArrayList<Customer> customers) {
        this.bankName = bankName;
        this.address = address;
        this.employees = new ArrayList<>();
        this.customers = customers;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public void loadEmployeeDatabase(String filename) {
        String path = "src/main/resources/" + filename + ".csv";
        File employeeDb = new File(path);

        try (Scanner scanner = new Scanner(employeeDb)) {
            while (scanner.hasNextLine()) {
                Employee employee = convertToEmployee(scanner.nextLine());
                this.employees.add(employee);
            }
        } catch (Exception e) {
            Main.logger.error(e.getMessage());
        }
    }

    public Employee convertToEmployee(String csvLine) {
        String[] delimitedCSVLine = csvLine.split(",");
        if (delimitedCSVLine.length != 4) {
            throw new InvalidCSVFileException();
        }

        String name = delimitedCSVLine[0];
        String department = delimitedCSVLine[1];
        double wage = Double.parseDouble(delimitedCSVLine[2]);
        String typeOfEmployee = delimitedCSVLine[3];

        return typeOfEmployee.equals("Manager") ? new Manager(name, department, wage) : new Teller(name, department, wage);
    }

    public void listEmployees() {
        for (Employee employee : employees) {
            System.out.println(employee);
            System.out.println();
            System.out.println(employee.getName() + "'s schedule:\n");
            employee.printSchedule();
            System.out.println();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bank bank)) return false;
        return Objects.equals(bankName, bank.bankName) && Objects.equals(address, bank.address) && Objects.equals(employees, bank.employees) && Objects.equals(customers, bank.customers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, address, employees, customers);
    }

    @Override
    public String toString() {
        return "Bank name : " + bankName + "\nAddress : " + address;
    }
}
