package com.ali.hunter.repository;

import com.ali.hunter.domain.entity.Agency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgencyRepository extends JpaRepository<Agency, UUID> {
    Page<Agency> findAll(Pageable pageable);
}
