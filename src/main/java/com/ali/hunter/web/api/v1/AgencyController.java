package com.ali.hunter.web.api.v1;

import com.ali.hunter.domain.entity.Agency;

import com.ali.hunter.exception.ResourceNotFoundException;
import com.ali.hunter.service.AgencyService;
import com.ali.hunter.web.vm.agency.AgencyMapper;
import com.ali.hunter.web.vm.agency.AgencyRequest;
import com.ali.hunter.web.vm.agency.AgencyResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/agencies")
@RequiredArgsConstructor
public class AgencyController {

    private final AgencyService agencyService;
    private final AgencyMapper agencyMapper;

    @GetMapping
    public Page<AgencyResponse> searchAgencies(AgencyRequest agencyRequest, Pageable pageable) {
        Agency agency = agencyMapper.toEntity(agencyRequest);
        return agencyService.searchAgency(agency, pageable)
                .map(agencyMapper::toResponse);
    }

    @PostMapping
    public AgencyResponse addAgency(@Valid @RequestBody AgencyRequest agencyRequest) {
        Agency agency = agencyMapper.toEntity(agencyRequest);
        Agency savedAgency = agencyService.addAgency(agency);
        return agencyMapper.toResponse(savedAgency);
    }

    @PutMapping("/{id}")
    public AgencyResponse updateAgency(@PathVariable UUID id, @Valid @RequestBody AgencyRequest agencyRequest) {
        Agency agency = agencyMapper.toEntity(agencyRequest);
        Agency updatedAgency = agencyService.updateAgency(id, agency);
        return agencyMapper.toResponse(updatedAgency);
    }

    @GetMapping("/{id}")
    public AgencyResponse getAgencyById(@PathVariable UUID id) {
        return agencyService.findById(id)
                .map(agencyMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Agency not found with id: " + id));
    }
}
