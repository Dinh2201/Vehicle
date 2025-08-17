package com.example.vehicle.entities.vehicle;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "vehicleType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_type_id")
    private String vehicleTypeId;
}
