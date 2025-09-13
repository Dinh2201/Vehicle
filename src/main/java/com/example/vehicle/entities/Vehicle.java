package com.example.vehicle.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_seq_gen")
    @SequenceGenerator(
            name = "vehicle_seq_gen",
            sequenceName = "vehicle_vehicle_id_seq",  // trùng với tên sequence trong PostgreSQL
            allocationSize = 1
    )
    @Column(name = "vehicle_id")
     long vehicleId;

    @Column(name = "vehicle_name")
     String vehicleName;

    @Column(name = "license_plate", unique = true, nullable = false)
     String licensePlate;

     String status;

    @Column(name = "signup_date")
     LocalDateTime signupDate;

    @Column(name = "latitude")
     Double latitude;

    @Column(name = "longitude")
     Double longitude;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id")
     VehicleType vehicleType;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "vehicle_drivers",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id")
    )
     Set<Driver> drivers = new HashSet<>();
}
