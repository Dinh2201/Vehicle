package com.example.vehicle.entities.vehicle;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private long vehicleId;

    @Column(name = "vehicle_name")
    private String vehicleName;

    @Column(name = "license_plate", unique = true, nullable = false)
    private String licensePlate;

    private String status;

    @Column(name = "signup_date")
    private LocalDate signupDate;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id")
    private VehicleType vehicleType;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "vehicle_drivers",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id")
    )
    private Set<Driver> drivers = new HashSet<>();
}
