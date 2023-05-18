package org.example.entities.employee;

import org.example.services.interfaces.Payable;

import java.util.HashMap;
import java.util.Map;

public class Teller extends Employee implements Payable {
    private double hourlyWage;
    private final int WEEKLY_HOURS = 40;

    private HashMap<String, String> schedule;

    public Teller(String name, String department, double hourlyWage) {
        super(name, department);
        this.hourlyWage = hourlyWage;
        schedule = new HashMap<>();
        schedule.put("Monday", "9:00am - 5:30pm");
        schedule.put("Tuesday", "10:00am - 6:30pm");
        schedule.put("Wednesday", "7:00am - 3:30pm");
        schedule.put("Thursday", "9:00am - 5:30pm");
        schedule.put("Friday", "10:00am - 6:30pm");
        schedule.put("Saturday", "Off");
        schedule.put("Sunday", "Off");
    }

    @Override
    public void printSchedule() {
        for (Map.Entry<String, String> day : schedule.entrySet()) {
            System.out.println(day.getKey() + " - " + day.getValue());
        }
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    @Override
    public void showPayCheck() {
        double weeklyPayCheck = hourlyWage * WEEKLY_HOURS;
        System.out.println("Your paycheck before tax deductions is $" + weeklyPayCheck);
    }
}

