package com.saikishore.expenseanalyzer.model;

public class Budget {

    private int id;
    private double monthlyBudget;

    public Budget() {
    }

    public Budget(int id, double monthlyBudget) {
        this.id = id;
        this.monthlyBudget = monthlyBudget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonthlyBudget() {
        return monthlyBudget;
    }

    public void setMonthlyBudget(double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
    }
}