package com.ali.hunter.service.impl;
import com.ali.hunter.domain.entity.Vehicle;
import com.ali.hunter.exception.ResourceNotFoundException;
import com.ali.hunter.repository.VehicleRepository;
import com.ali.hunter.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class VehicleServiceImpl implements  VehicleService {
    private final VehicleRepository vehicleRepository;

    @Override
    public Page<Vehicle> searchVehicles(Vehicle vehicle, Pageable pageable) {
        if (vehicle.getBrand() == null && vehicle.getModel() == null) {
            return vehicleRepository.findAll(pageable);
        }

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Vehicle> example = Example.of(vehicle, matcher);
        return vehicleRepository.findAll(example, pageable);
    }

    @Override
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle updateVehicle(UUID id, Vehicle vehicle) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        existingVehicle.setRegNum(vehicle.getRegNum());
        existingVehicle.setBrand(vehicle.getBrand());
        existingVehicle.setModel(vehicle.getModel());
        existingVehicle.setFuelType(vehicle.getFuelType());
        existingVehicle.setVehicleType(vehicle.getVehicleType());
        existingVehicle.setDailyPrice(vehicle.getDailyPrice());
        existingVehicle.setDescription(vehicle.getDescription());
        existingVehicle.setAgency(vehicle.getAgency());

        return vehicleRepository.save(existingVehicle);
    }

    @Override
    public Optional<Vehicle> findById(UUID id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public Page<Vehicle> findByAgencyId(UUID agenceId, Pageable pageable) {
        return vehicleRepository.findByAgenceId(agenceId, pageable);
    }

}
