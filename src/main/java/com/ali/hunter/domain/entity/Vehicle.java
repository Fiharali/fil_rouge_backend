package com.ali.hunter.domain.entity;

import com.ali.hunter.domain.enums.FuelType;
import com.ali.hunter.domain.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String regNum;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    private String brand;
    private String model;
    private Double dailyPrice;
    private String description;
    private LocalDate deleted_at ;
    private LocalDate created_atb = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "agence_id")
    private Agency agency;

    @OneToMany(mappedBy = "vehicle")
    private List<Booking> bookings;

    @OneToMany(mappedBy ="vehicle")
    private List<Review> reviews;
}
