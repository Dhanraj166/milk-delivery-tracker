package com.milktracker.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.milktracker.backend.entity.PriceSetting;

public interface PriceSettingRepository extends JpaRepository<PriceSetting, Long> {
    
}