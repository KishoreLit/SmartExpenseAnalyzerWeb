
package com.saikishore.expenseanalyzer.model;
import java.sql.Timestamp;
public class User {

    private int id;
    private String fullName;
    private String email;
    private String password;
    private Timestamp createdAt;

    public User() {
    }

    public User(int id, String fullName, String email,
        String password, Timestamp createdAt) {

this.id = id;
this.fullName = fullName;
this.email = email;
this.password = password;
this.createdAt = createdAt;
}
    public User(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}