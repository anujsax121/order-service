package com.orderservice.model;

import jakarta.validation.constraints.NotBlank;

public class OrderDto {
    private String id;
    @NotBlank(message = "name should not empty")
    private String name;
    private int quantity;
    private double amount;

    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
