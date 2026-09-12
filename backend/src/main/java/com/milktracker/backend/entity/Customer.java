package com.milktracker.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private Double dailyQty;
    private Double rate;

    public Customer() {
    }

    public Customer(Long id, String name, String address, Double dailyQty, Double rate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dailyQty = dailyQty;
        this.rate = rate;
    }

	public Customer(String string, String string2, double qty, double rate2) {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getDailyQty() {
        return dailyQty;
    }

    public void setDailyQty(Double dailyQty) {
        this.dailyQty = dailyQty;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}	