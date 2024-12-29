package com.ali.hunter.web.vm.booking;


import com.ali.hunter.domain.enums.BookingStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class BookingResponse {
    private UUID id;
    private UUID userId;
    private UUID vehicleId;
    private BookingStatus bookingStatus;
}
