package org.example.entities.bank;

import org.example.entities.account.Account;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Customer {
    private String name;
    private Set<Account> customerAccounts;

    public Customer(String name) {
        this.name = name;
        this.customerAccounts = new HashSet<>();
    }

    public Set<Account> getCustomerAccounts() {
        return customerAccounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAccount(Account account) {
        this.customerAccounts.add(account);
    }

    public void listAccounts() {
        for (Account account : customerAccounts) {
            System.out.println(account);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(name, customer.name) && Objects.equals(customerAccounts, customer.customerAccounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, customerAccounts);
    }

    @Override
    public String toString() {
        return "Customer name : " + name;
    }
}

