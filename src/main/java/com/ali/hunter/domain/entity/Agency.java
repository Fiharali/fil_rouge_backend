package com.ali.hunter.domain.entity;

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
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String address;
    private LocalDate deleted_at;
    private LocalDate created_atb = LocalDate.now();
    @OneToMany(mappedBy = "agency")
    private List<Vehicle> vehicles;

}