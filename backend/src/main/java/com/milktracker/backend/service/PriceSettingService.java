package com.milktracker.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktracker.backend.entity.PriceSetting;
import com.milktracker.backend.repository.PriceSettingRepository;

@Service
public class PriceSettingService {

    private final PriceSettingRepository priceSettingRepository;
    private static final Long SETTINGS_ID = 1L;

    @Autowired
    public PriceSettingService(PriceSettingRepository priceSettingRepository) {
        this.priceSettingRepository = priceSettingRepository;
    }

    // Get current price settings (creates a default one if none exists yet)
    public PriceSetting getCurrentPrices() {
        return priceSettingRepository.findById(SETTINGS_ID)
                .orElseGet(() -> {
                    // No settings saved yet — create a default starting row
                    PriceSetting defaultSettings = new PriceSetting(SETTINGS_ID, 50.0, 20.0);
                    return priceSettingRepository.save(defaultSettings);
                });
    }

    // Update prices
    public PriceSetting updatePrices(Double milkPrice, Double waterPrice) {
        PriceSetting settings = getCurrentPrices(); // ensures row exists first
        settings.setMilkPricePerLiter(milkPrice);
        settings.setWaterPricePerLiter(waterPrice);
        return priceSettingRepository.save(settings);
    }
}