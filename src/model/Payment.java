package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Payment {
    private String name;
    private double amount;
    private LocalDate date;
    private List<Payment> payments = new ArrayList<>();

    public Payment (String name, double amount, LocalDate date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    public String getMemberName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
