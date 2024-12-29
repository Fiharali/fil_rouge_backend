package com.ali.hunter.web.vm.review;

import lombok.Data;

import java.util.UUID;

@Data
public class ReviewResponse {
    private UUID id;
    private int rating;
    private String comment;
    private UUID vehicleId;
}
