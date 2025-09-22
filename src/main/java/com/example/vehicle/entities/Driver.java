package com.example.vehicle.entities;

import com.example.vehicle.enums.DriverStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "driver")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver_seq_gen")
    @SequenceGenerator(
            name = "driver_seq_gen",
            sequenceName = "driver_driver_id_seq",  // trùng với tên sequence trong PostgreSQL
            allocationSize = 1
    )
    @Column(name = "driver_id")
     long driverId;

     String name;

     String age;

     String phoneNumber;

     String address;

    @Column(name = "identity_card")
     String identityCard;

     String sex;

    @Column(name = "driver_license")
     String driverLicense;

    @Enumerated(EnumType.STRING)
     DriverStatus status;


    @Column(name = "avg_rating", precision = 5, scale = 2)
     BigDecimal avgRating;

    @OneToOne(mappedBy = "driver")
     Vehicle vehicle;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DriverNotification> notifications = new ArrayList<>();

}
