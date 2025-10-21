package com.infosys.budgettracker.dto;

import java.time.LocalDate;

public class TransactionDTO {
    private Long id;
    private String type;
    private String category;
    private double amount;
    private String description;
    private String account;
    private LocalDate date;
    private String username; // only username, no password/email

    public TransactionDTO(Long id, String type, String category, double amount,
                          String description, String account, LocalDate date, String username) {
        this.id = id;
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.account = account;
        this.date = date;
        this.username = username;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
