package com.milktracker.backend.dto;

public class DailyStat {

    private String date;      
    private double liters;
    private double revenue;

    public DailyStat() {
    }

    public DailyStat(String date, double liters, double revenue) {
        this.date = date;
        this.liters = liters;
        this.revenue = revenue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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