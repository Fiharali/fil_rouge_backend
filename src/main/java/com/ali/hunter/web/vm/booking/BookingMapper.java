package com.ali.hunter.web.vm.booking;


import com.ali.hunter.domain.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    Booking toEntity(BookingRequest bookingRequest);

    @Mapping(target = "id", source = "id")
    BookingResponse toResponse(Booking booking);
}
