package com.ali.hunter.web.vm.agency;

import com.ali.hunter.domain.entity.Agency;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AgencyMapper {
    AgencyMapper INSTANCE = Mappers.getMapper(AgencyMapper.class);

    Agency toEntity(com.ali.hunter.web.vm.agency.AgencyRequest agencyRequest);

    @Mapping(target = "id", source = "id")
    com.ali.hunter.web.vm.agency.AgencyResponse toResponse(Agency agency);
}
