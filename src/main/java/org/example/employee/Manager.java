package org.example.employee;

import org.example.interfaces.Payable;

import java.util.HashMap;
import java.util.Map;

public class Manager extends Employee implements Payable {
    private double salary;
    private HashMap<String, String> schedule;

    public Manager(String name, String department, double salary) {
        super(name, department);
        this.salary = salary;
        schedule = new HashMap<>();

        schedule.put("Monday", "9:00am - 5:30pm");
        schedule.put("Tuesday", "9:00am - 5:30pm");
        schedule.put("Wednesday", "9:00am - 5:30pm");
        schedule.put("Thursday", "9:00am - 5:30pm");
        schedule.put("Friday", "9:00am - 5:30pm");
        schedule.put("Saturday", "Off");
        schedule.put("Sunday", "9:00am - 5:30pm");
    }

    @Override
    public void printSchedule() {
        for (Map.Entry<String, String> day : schedule.entrySet()) {
            System.out.println(day.getKey() + " - " + day.getValue());
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public void showPayCheck() {
        double monthlyPayCheck = salary / 12;
        System.out.println("Your monthly paycheck before taxes is $" + monthlyPayCheck);
    }
}