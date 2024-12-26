package com.ali.hunter.service;



import com.ali.hunter.domain.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.UUID;

public interface VehicleService {
    Page<Vehicle> searchVehicles(Vehicle vehicle, Pageable pageable);
    Vehicle addVehicle(Vehicle vehicle);
    Vehicle updateVehicle(UUID id, Vehicle vehicle);
    Optional<Vehicle> findById(UUID id);
    Page<Vehicle> findByAgencyId(UUID agencyId, Pageable pageable);
}
