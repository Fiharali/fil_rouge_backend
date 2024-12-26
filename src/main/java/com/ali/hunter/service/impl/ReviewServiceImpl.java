package com.ali.hunter.service.impl;


import com.ali.hunter.domain.entity.Review;
import com.ali.hunter.exception.ResourceNotFoundException;
import com.ali.hunter.repository.ReviewRepository;
import com.ali.hunter.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor

public class ReviewServiceImpl implements ReviewService {


    private final ReviewRepository reviewRepository;



    @Override
    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(UUID id, Review review) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        existingReview.setRating(review.getRating());
        existingReview.setComment(review.getComment());

        return reviewRepository.save(existingReview);
    }

    @Override
    public Optional<Review> findById(UUID id) {
        return reviewRepository.findById(id);
    }

    @Override
    public Page<Review> findByVehicleId(UUID vehicleId, Pageable pageable) {
        return reviewRepository.findByVehicleId(vehicleId, pageable);
    }
}
