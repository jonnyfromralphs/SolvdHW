package org.example.services;

import org.example.entities.employee.Employee;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class BankService {
    public ArrayList<Employee> getEmployeesWithFilter(Predicate<Employee> filterEmployee, ArrayList<Employee> employees) {
        ArrayList<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (filterEmployee.test(employee)) {
                filteredEmployees.add(employee);
            }
        }
        return filteredEmployees;
    }

    public ArrayList<String> transformEmployees(Function<Employee, String> transformEmployee, ArrayList<Employee> employees) {
        ArrayList<String> employeesTransformed = new ArrayList<>();
        for (Employee employee : employees) {
            employeesTransformed.add(transformEmployee.apply(employee));
        }
        return employeesTransformed;
    }

    public void customGreetingWithConsumer(Consumer<Employee> employeeConsumer, ArrayList<Employee> employees) {
        for (Employee employee : employees) {
            employeeConsumer.accept(employee);
        }
    }
}
