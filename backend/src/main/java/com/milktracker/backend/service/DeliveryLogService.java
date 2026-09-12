package com.milktracker.backend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktracker.backend.dto.BillResponse;
import com.milktracker.backend.dto.CustomerContribution;
import com.milktracker.backend.dto.DailyStat;
import com.milktracker.backend.dto.DashboardSummary;
import com.milktracker.backend.entity.Customer;
import com.milktracker.backend.entity.DeliveryLog;
import com.milktracker.backend.repository.DeliveryLogRepository;

@Service
public class DeliveryLogService {

    private final DeliveryLogRepository deliveryLogRepository;
    private final CustomerService customerService;

    @Autowired
    public DeliveryLogService(DeliveryLogRepository deliveryLogRepository, CustomerService customerService) {
        this.deliveryLogRepository = deliveryLogRepository;
        this.customerService = customerService;
    }

    // Mark (or update) delivery status for a customer on a specific date
    public DeliveryLog markDelivery(Long customerId, LocalDate date, Boolean delivered) {

        Optional<DeliveryLog> existingLog = deliveryLogRepository.findByCustomer_IdAndDate(customerId, date);

        if (existingLog.isPresent()) {
            // Already marked for this date — just update it 
            DeliveryLog log = existingLog.get();
            log.setDelivered(delivered);
            return deliveryLogRepository.save(log);
        } else {
            // No log yet for this date — create a new one
            Customer customer = customerService.getCustomerById(customerId);
            DeliveryLog newLog = new DeliveryLog(customer, date, delivered);
            return deliveryLogRepository.save(newLog);
        }
    }

    // Get all delivery logs for one customer 
    public List<DeliveryLog> getLogsForCustomer(Long customerId) {
        return deliveryLogRepository.findByCustomer_Id(customerId);
    }
    
    public BillResponse calculateMonthlyBill(Long customerId, int year, int month) {

        Customer customer = customerService.getCustomerById(customerId);
        List<DeliveryLog> allLogs = deliveryLogRepository.findByCustomer_Id(customerId);

        // Filter logs: only this year+month, and only delivered = true
        long deliveredDaysCount = 0;

        for (DeliveryLog log : allLogs) {
        	
            if (log.getDate().getYear() == year
                    && log.getDate().getMonthValue() == month
                    && Boolean.TRUE.equals(log.getDelivered())) 
            {
                deliveredDaysCount++;
            }
        }	

        double totalLiters = deliveredDaysCount * customer.getDailyQty();
        double totalAmount = totalLiters * customer.getRate();

        return new BillResponse(
                customer.getId(),
                customer.getName(),
                year,
                month,
                deliveredDaysCount,
                totalLiters,
                totalAmount
        );
    }
    
 // Dashboard summary across ALL customers for a given month
    public DashboardSummary getDashboardSummary(int year, int month) {

        List<Customer> allCustomers = customerService.getAllCustomers();

        long totalDeliveredEntries = 0;
        double totalLiters = 0;
        double totalRevenue = 0;

        for (Customer customer : allCustomers) {
            BillResponse bill = calculateMonthlyBill(customer.getId(), year, month);
            totalDeliveredEntries += bill.getDeliveredDays();
            totalLiters += bill.getTotalLiters();
            totalRevenue += bill.getTotalAmount();
        }

        return new DashboardSummary(
                year,
                month,
                totalDeliveredEntries,
                totalLiters,
                totalRevenue,
                allCustomers.size()
        );
    }
    
 // Daily breakdown for the whole month (for a line/bar chart)
    public List<DailyStat> getDailyBreakdown(int year, int month) {

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        List<DeliveryLog> logsInMonth = deliveryLogRepository.findByDateBetween(startDate, endDate);

        // Group logs by date, keeping only delivered=true ones
        Map<LocalDate, List<DeliveryLog>> groupedByDate = logsInMonth.stream()
                .filter(log -> Boolean.TRUE.equals(log.getDelivered()))
                .collect(Collectors.groupingBy(DeliveryLog::getDate));

        List<DailyStat> result = new ArrayList<>();

        for (Map.Entry<LocalDate, List<DeliveryLog>> entry : groupedByDate.entrySet()) {
            double dayLiters = 0;
            double dayRevenue = 0;

            for (DeliveryLog log : entry.getValue()) {
                Customer customer = log.getCustomer();
                dayLiters += customer.getDailyQty();
                dayRevenue += customer.getDailyQty() * customer.getRate();
            }

            result.add(new DailyStat(entry.getKey().toString(), dayLiters, dayRevenue));
        }

        // Sort by date ascending, so the chart draws left-to-right correctly
        result.sort(Comparator.comparing(DailyStat::getDate));

        return result;
    }

    // Revenue/liters contribution per customer (for a pie/bar chart)
    public List<CustomerContribution> getCustomerBreakdown(int year, int month) {

        List<Customer> allCustomers = customerService.getAllCustomers();
        List<CustomerContribution> result = new ArrayList<>();

        for (Customer customer : allCustomers) {
            BillResponse bill = calculateMonthlyBill(customer.getId(), year, month);
            result.add(new CustomerContribution(customer.getName(), bill.getTotalLiters(), bill.getTotalAmount()));
        }

        // Sort highest revenue first — most useful order for a chart legend
        result.sort((a, b) -> Double.compare(b.getRevenue(), a.getRevenue()));

        return result;
    }
    
}