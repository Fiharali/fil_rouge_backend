package com.ali.hunter.web.vm.booking;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class BookingRequest {
    @NotNull(message = "User ID is mandatory")
    private UUID userId;

    @NotNull(message = "Vehicle ID is mandatory")
    private UUID vehicleId;
}
