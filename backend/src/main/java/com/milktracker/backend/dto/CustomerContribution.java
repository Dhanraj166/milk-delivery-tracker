package com.milktracker.backend.dto;

public class CustomerContribution {

    private String customerName;
    private double liters;
    private double revenue;

    public CustomerContribution() {
    }

    public CustomerContribution(String customerName, double liters, double revenue) {
        this.customerName = customerName;
        this.liters = liters;
        this.revenue = revenue;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getLiters() {
        return liters;
    }

    public void setLiters(double liters) {
        this.liters = liters;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}