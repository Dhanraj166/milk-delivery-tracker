package com.milktracker.backend.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.milktracker.backend.entity.DeliveryLog;

public interface DeliveryLogRepository extends JpaRepository<DeliveryLog, Long> {

    // Find a delivery log for a specific customer on a specific date
    Optional<DeliveryLog> findByCustomer_IdAndDate(Long customerId, LocalDate date);

    // Find all delivery logs for a specific customer 
    List<DeliveryLog> findByCustomer_Id(Long customerId);
    
    // get all logs between two dates
    List<DeliveryLog> findByDateBetween(LocalDate startDate, LocalDate endDate);
}