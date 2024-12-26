package com.ali.hunter.service.impl;


import com.ali.hunter.domain.entity.Agency;
import com.ali.hunter.exception.ResourceNotFoundException;
import com.ali.hunter.repository.AgencyRepository;
import com.ali.hunter.service.AgencyService;
import com.ali.hunter.service.AgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class AgencyServiceImpl implements AgencyService {
    private final AgencyRepository agencyRepository;

    @Override
    public Page<Agency> searchAgency(Agency agency, Pageable pageable) {
        if (agency.getName() == null && agency.getAddress() == null) {
            return agencyRepository.findAll(pageable);
        }

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Agency> example = Example.of(agency, matcher);
        return agencyRepository.findAll(example, pageable);
    }

    @Override
    public Agency addAgency(Agency agency) {
        return agencyRepository.save(agency);
    }

    @Override
    public Agency updateAgency(UUID id, Agency agency) {
        Agency existingAgency = agencyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agency not found"));

        existingAgency.setName(agency.getName());
        existingAgency.setAddress(agency.getAddress());

        return agencyRepository.save(existingAgency);
    }

    @Override
    public Optional<Agency> findById(UUID id) {
        return agencyRepository.findById(id);
    }
}
