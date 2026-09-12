package com.milktracker.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.milktracker.backend.dto.CustomerContribution;
import com.milktracker.backend.dto.DailyStat;
import com.milktracker.backend.dto.DashboardSummary;
import com.milktracker.backend.service.DeliveryLogService;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardController {

    private final DeliveryLogService deliveryLogService;

    @Autowired
    public DashboardController(DeliveryLogService deliveryLogService) {
        this.deliveryLogService = deliveryLogService;
    }

    @GetMapping("/summary")
    public DashboardSummary getSummary(
            @RequestParam int year,
            @RequestParam int month) {

        return deliveryLogService.getDashboardSummary(year, month);
    }
    
    // GET /api/dashboard/daily-breakdown?year=2026&month=9
    @GetMapping("/daily-breakdown")
    public List<DailyStat> getDailyBreakdown(
            @RequestParam int year,
            @RequestParam int month) {
        return deliveryLogService.getDailyBreakdown(year, month);
    }

    // GET /api/dashboard/customer-breakdown?year=2026&month=9
    @GetMapping("/customer-breakdown")
    public List<CustomerContribution> getCustomerBreakdown(
            @RequestParam int year,
            @RequestParam int month) {
        return deliveryLogService.getCustomerBreakdown(year, month);
    }
}