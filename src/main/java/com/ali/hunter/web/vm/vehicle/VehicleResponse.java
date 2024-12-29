package com.ali.hunter.web.vm.vehicle;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class VehicleResponse {
    private UUID id;
    private String regNum;
    private String brand;
    private String model;
    private String fuelType;
    private String vehicleType;
    private BigDecimal dailyPrice;
    private String description;
    private UUID agencyId;
}
