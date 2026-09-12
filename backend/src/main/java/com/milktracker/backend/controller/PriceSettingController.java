package com.milktracker.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.milktracker.backend.entity.PriceSetting;
import com.milktracker.backend.service.PriceSettingService;

@RestController
@RequestMapping("/api/settings")
@CrossOrigin(origins = "http://localhost:5173")
public class PriceSettingController {

    private final PriceSettingService priceSettingService;

    @Autowired
    public PriceSettingController(PriceSettingService priceSettingService) {
        this.priceSettingService = priceSettingService;
    }

    @GetMapping("/prices")
    public PriceSetting getCurrentPrices() {
        return priceSettingService.getCurrentPrices();
    }

    @PutMapping("/prices")
    public PriceSetting updatePrices(@RequestBody PriceSetting priceSetting) {
        return priceSettingService.updatePrices(
                priceSetting.getMilkPricePerLiter(),
                priceSetting.getWaterPricePerLiter()
        );
    }
}