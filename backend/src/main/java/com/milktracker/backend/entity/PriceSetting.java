package com.milktracker.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "price_settings")
public class PriceSetting {

    @Id
    private Long id; 

    private Double milkPricePerLiter;

    private Double waterPricePerLiter;

    public PriceSetting() {
    }

    public PriceSetting(Long id, Double milkPricePerLiter, Double waterPricePerLiter) {
        this.id = id;
        this.milkPricePerLiter = milkPricePerLiter;
        this.waterPricePerLiter = waterPricePerLiter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMilkPricePerLiter() {
        return milkPricePerLiter;
    }

    public void setMilkPricePerLiter(Double milkPricePerLiter) {
        this.milkPricePerLiter = milkPricePerLiter;
    }

    public Double getWaterPricePerLiter() {
        return waterPricePerLiter;
    }

    public void setWaterPricePerLiter(Double waterPricePerLiter) {
        this.waterPricePerLiter = waterPricePerLiter;
    }
}