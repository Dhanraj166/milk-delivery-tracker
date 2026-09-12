package com.milktracker.backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.milktracker.backend.dto.BillResponse;
import com.milktracker.backend.entity.DeliveryLog;
import com.milktracker.backend.service.DeliveryLogService;

@RestController
@RequestMapping("/api/delivery-logs")
@CrossOrigin(origins = "http://localhost:5173")
public class DeliveryLogController {

    private final DeliveryLogService deliveryLogService;

    @Autowired
    public DeliveryLogController(DeliveryLogService deliveryLogService) {
        this.deliveryLogService = deliveryLogService;
    }

    // Mark delivery for a customer on a given date
    @PostMapping("/mark")
    public DeliveryLog markDelivery(
            @RequestParam Long customerId,
            @RequestParam String date,
            @RequestParam Boolean delivered) {

        LocalDate parsedDate = LocalDate.parse(date); 
        return deliveryLogService.markDelivery(customerId, parsedDate, delivered);
    }

    // Get all logs for one customer
    @GetMapping("/customer/{customerId}")
    public List<DeliveryLog> getLogsForCustomer(@PathVariable Long customerId) {
        return deliveryLogService.getLogsForCustomer(customerId);
    }
    
    //  Get monthly bill for a customer
    @GetMapping("/bill")
    public BillResponse getMonthlyBill(
            @RequestParam Long customerId,
            @RequestParam int year,
            @RequestParam int month) {

        return deliveryLogService.calculateMonthlyBill(customerId, year, month);
    }
    
}