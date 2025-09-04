package com.example.vehicle.entities.vehicle;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_seq_gen")
    @SequenceGenerator(
            name = "vehicle_seq_gen",
            sequenceName = "vehicle_vehicle_id_seq",  // trùng với tên sequence trong PostgreSQL
            allocationSize = 1
    )
    @Column(name = "vehicle_id")
    private long vehicleId;

    @Column(name = "vehicle_name")
    private String vehicleName;

    @Column(name = "license_plate", unique = true, nullable = false)
    private String licensePlate;

    private String status;

    @Column(name = "signup_date")
    private LocalDateTime signupDate;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

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
