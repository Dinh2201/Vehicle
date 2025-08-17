package com.example.vehicle.entities.vehicle;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private String vehicleId;

    @Column(name = "vehicle_name")
    private String vehicleName;

    @Column(name = "license_plate", unique = true, nullable = false)
    private String licensePlate;

    private String status;

    @Column(name = "signup_date")
    private LocalDate signupDate;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id")
    private VehicleTypeEntity vehicleType;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private DriverEntity driver;

}
