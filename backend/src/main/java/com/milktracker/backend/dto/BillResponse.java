package com.milktracker.backend.dto;

public class BillResponse {

    private Long customerId;
    private String customerName;
    private int year;
    private int month;
    private long deliveredDays;
    private double totalLiters;
    private double totalAmount;

    public BillResponse() {
    }

    public BillResponse(Long customerId, String customerName, int year, int month,
                         long deliveredDays, double totalLiters, double totalAmount) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.year = year;
        this.month = month;
        this.deliveredDays = deliveredDays;
        this.totalLiters = totalLiters;
        this.totalAmount = totalAmount;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public long getDeliveredDays() {
        return deliveredDays;
    }

    public void setDeliveredDays(long deliveredDays) {
        this.deliveredDays = deliveredDays;
    }

    public double getTotalLiters() {
        return totalLiters;
    }

    public void setTotalLiters(double totalLiters) {
        this.totalLiters = totalLiters;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}