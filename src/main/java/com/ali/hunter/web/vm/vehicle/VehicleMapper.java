package com.ali.hunter.web.vm.vehicle;

import com.ali.hunter.domain.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    @Mapping(target = "agency.id", source = "agencyId")
    Vehicle toEntity(VehicleRequest vehicleRequest);

    @Mapping(target = "agencyId", source = "agency.id")
    VehicleResponse toResponse(Vehicle vehicle);
}
