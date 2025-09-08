package com.example.vehicle.entities.vehicle;

import com.example.vehicle.enums.DriverStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "driver")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver_seq_gen")
    @SequenceGenerator(
            name = "driver_seq_gen",
            sequenceName = "driver_driver_id_seq",  // trùng với tên sequence trong PostgreSQL
            allocationSize = 1
    )
        @Column(name = "driver_id")
    private long driverId;

    private String name;

    private String age;

    private String phoneNumber;

    private String address;

    @Column(name = "identity_card")
    private String identityCard;

    private String sex;

    @Column(name = "driver_license")
    private String driverLicense;

    @Enumerated(EnumType.STRING)
    private DriverStatus status;


    @Column(name = "avg_rating", precision = 5, scale = 2)
    private BigDecimal avgRating;

    @ManyToMany(mappedBy = "drivers")
    private Set<Vehicle> vehicles = new HashSet<>();

}
