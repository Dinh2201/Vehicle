package com.example.vehicle.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity(name = "vehicleType")
@Table(name = "vehicle_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_type_seq_gen")
    @SequenceGenerator(
            name = "vehicle_type_seq_gen",
            sequenceName = "vehicle_type_vehicle_type_id_seq",  // trùng với tên sequence trong PostgreSQL
            allocationSize = 1
    )
    @Column(name = "vehicle_type_id")
     long vehicleTypeId;

     String name;

     String description;

    @OneToMany(mappedBy = "vehicleType")
    @JsonIgnore
     List<Vehicle> vehicles ;
}
