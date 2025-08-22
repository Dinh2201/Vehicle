package com.example.vehicle.entities.vehicle;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "vehicleType")
@Table(name = "vehicle_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_type_id")
    private long vehicleTypeId;

    private String name;

    private String description;

    @OneToMany(mappedBy = "vehicleType")
    @JsonIgnore
    private List<Vehicle> vehicles ;
}
