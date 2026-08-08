package com.saikishore.expenseanalyzer.model;

import java.time.LocalDate;

public class Expense {

    private int id;
    private String title;
    private double amount;
    private String category;
    private LocalDate expenseDate;
    private String notes;
    private int userId;

    public Expense() {
    }

    public Expense(int id, String title, double amount, String category, LocalDate expenseDate, String notes) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.expenseDate = expenseDate;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                ", expenseDate=" + expenseDate +
                ", notes='" + notes + '\'' +
                '}';
    }
}