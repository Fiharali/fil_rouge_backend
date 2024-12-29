package com.ali.hunter.web.api.v1;

import com.ali.hunter.domain.entity.Review;
import com.ali.hunter.exception.ResourceNotFoundException;
import com.ali.hunter.service.ReviewService;
import com.ali.hunter.web.vm.review.ReviewMapper;
import com.ali.hunter.web.vm.review.ReviewRequest;
import com.ali.hunter.web.vm.review.ReviewResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @PostMapping
    public ReviewResponse addReview(@Valid @RequestBody ReviewRequest reviewRequest) {
        Review review = reviewMapper.toEntity(reviewRequest);
        Review savedReview = reviewService.addReview(review);
        return reviewMapper.toResponse(savedReview);
    }

    @PutMapping("/{id}")
    public ReviewResponse updateReview(@PathVariable UUID id, @Valid @RequestBody ReviewRequest reviewRequest) {
        Review review = reviewMapper.toEntity(reviewRequest);
        Review updatedReview = reviewService.updateReview(id, review);
        return reviewMapper.toResponse(updatedReview);
    }

    @GetMapping("/{id}")
    public ReviewResponse getReviewById(@PathVariable UUID id) {
        return reviewService.findById(id)
                .map(reviewMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
    }

    @GetMapping("/vehicle/{vehicleId}")
    public Page<ReviewResponse> getReviewsByVehicleId(@PathVariable UUID vehicleId, Pageable pageable) {
        return reviewService.findByVehicleId(vehicleId, pageable)
                .map(reviewMapper::toResponse);
    }
}
