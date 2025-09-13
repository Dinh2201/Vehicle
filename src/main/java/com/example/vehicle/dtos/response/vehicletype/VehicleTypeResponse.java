package com.example.vehicle.dtos.response.vehicletype;

import com.example.vehicle.dtos.response.vehicle.VehicleResponseNoVehicleType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleTypeResponse {
    Long vehicleTypeId;

    String name;

    String description;

    List<VehicleResponseNoVehicleType> vehicles;
}
