package com.orderservice.entity;


import com.orderservice.model.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "order")
public class Order {
    @Id
    private String id;

    private String name;

    private int quantity;

    private double amount;

    private Status status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
