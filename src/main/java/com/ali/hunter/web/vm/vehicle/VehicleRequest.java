package com.ali.hunter.web.vm.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class VehicleRequest {

    @NotBlank(message = "Registration number is mandatory")
    @Size(max = 50, message = "Registration number cannot exceed 50 characters")
    private String regNum;

    @NotBlank(message = "Brand is mandatory")
    @Size(max = 100, message = "Brand cannot exceed 100 characters")
    private String brand;

    @NotBlank(message = "Model is mandatory")
    @Size(max = 100, message = "Model cannot exceed 100 characters")
    private String model;

    @NotBlank(message = "Fuel type is mandatory")
    private String fuelType;

    @NotBlank(message = "Vehicle type is mandatory")
    private String vehicleType;

    @NotNull(message = "Daily price is mandatory")
    private BigDecimal dailyPrice;

    private String description;

    @NotNull(message = "Agency ID is mandatory")
    private UUID agencyId;
}
