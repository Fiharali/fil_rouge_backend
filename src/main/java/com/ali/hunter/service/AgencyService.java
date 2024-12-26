package com.ali.hunter.service;



import com.ali.hunter.domain.entity.Agency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.UUID;

public interface AgencyService {
    Page<Agency> searchAgency(Agency agency, Pageable pageable);
    Agency addAgency(Agency agency);
    Agency updateAgency(UUID id, Agency agency);
    Optional<Agency> findById(UUID id);
}