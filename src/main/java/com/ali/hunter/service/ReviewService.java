package com.ali.hunter.service;


import com.ali.hunter.domain.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.UUID;
public interface ReviewService {

    Review addReview(Review review);
    Review updateReview(UUID id, Review review);
    Optional<Review> findById(UUID id);
    Page<Review> findByVehicleId(UUID vehicleId, Pageable pageable);
}
