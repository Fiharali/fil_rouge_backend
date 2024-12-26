package com.ali.hunter.repository;

import com.ali.hunter.domain.entity.User;
import com.ali.hunter.domain.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    Page<Vehicle> findAll(Pageable pageable);
    Page<Vehicle> findByAgencyId(UUID agenceId, Pageable pageable);

    Page<Vehicle> findByAgenceId(UUID agenceId, Pageable pageable);
}
