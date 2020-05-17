package com.backendsystemforautodealership.client.prices;

import java.math.BigDecimal;

/**
 * In this class we represent the price of a vehicle
 */
public class Price {

    private String currency;
    private BigDecimal price;
    private Long vehicleId;

    public Price() {
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
