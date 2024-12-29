package com.ali.hunter.web.api.v1;

import com.ali.hunter.domain.entity.Vehicle;
import com.ali.hunter.exception.ResourceNotFoundException;
import com.ali.hunter.service.VehicleService;
import com.ali.hunter.web.vm.vehicle.VehicleMapper;
import com.ali.hunter.web.vm.vehicle.VehicleRequest;
import com.ali.hunter.web.vm.vehicle.VehicleResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;
    private final VehicleMapper vehicleMapper;

    @GetMapping
    public Page<VehicleResponse> searchVehicles(VehicleRequest vehicleRequest, Pageable pageable) {
        Vehicle vehicle = vehicleMapper.toEntity(vehicleRequest);
        return vehicleService.searchVehicles(vehicle, pageable)
                .map(vehicleMapper::toResponse);
    }

    @PostMapping
    public VehicleResponse addVehicle(@Valid @RequestBody VehicleRequest vehicleRequest) {
        Vehicle vehicle = vehicleMapper.toEntity(vehicleRequest);
        Vehicle savedVehicle = vehicleService.addVehicle(vehicle);
        return vehicleMapper.toResponse(savedVehicle);
    }

    @PutMapping("/{id}")
    public VehicleResponse updateVehicle(@PathVariable UUID id, @Valid @RequestBody VehicleRequest vehicleRequest) {
        Vehicle vehicle = vehicleMapper.toEntity(vehicleRequest);
        Vehicle updatedVehicle = vehicleService.updateVehicle(id, vehicle);
        return vehicleMapper.toResponse(updatedVehicle);
    }

    @GetMapping("/{id}")
    public VehicleResponse getVehicleById(@PathVariable UUID id) {
        return vehicleService.findById(id)
                .map(vehicleMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));
    }

    @GetMapping("/agency/{agencyId}")
    public Page<VehicleResponse> findByAgencyId(@PathVariable UUID agencyId, Pageable pageable) {
        return vehicleService.findByAgencyId(agencyId, pageable)
                .map(vehicleMapper::toResponse);
    }
}
