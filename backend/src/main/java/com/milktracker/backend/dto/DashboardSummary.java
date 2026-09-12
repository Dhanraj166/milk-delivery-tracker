package com.milktracker.backend.dto;

public class DashboardSummary {

    private int year;
    private int month;
    private long totalDeliveredEntries;
    private double totalLitersSold;
    private double totalRevenue;
    private int totalCustomers;

    public DashboardSummary() {
    }

    public DashboardSummary(int year, int month, long totalDeliveredEntries,
                             double totalLitersSold, double totalRevenue, int totalCustomers) {
        this.year = year;
        this.month = month;
        this.totalDeliveredEntries = totalDeliveredEntries;
        this.totalLitersSold = totalLitersSold;
        this.totalRevenue = totalRevenue;
        this.totalCustomers = totalCustomers;
    }

    // ---- Getters and Setters ----

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

    public long getTotalDeliveredEntries() {
        return totalDeliveredEntries;
    }

    public void setTotalDeliveredEntries(long totalDeliveredEntries) {
        this.totalDeliveredEntries = totalDeliveredEntries;
    }

    public double getTotalLitersSold() {
        return totalLitersSold;
    }

    public void setTotalLitersSold(double totalLitersSold) {
        this.totalLitersSold = totalLitersSold;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public int getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }
}