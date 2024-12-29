package com.ali.hunter.web.vm.review;

import com.ali.hunter.domain.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    Review toEntity(ReviewRequest reviewRequest);

    @Mapping(target = "id", source = "id")
    ReviewResponse toResponse(Review review);
}
