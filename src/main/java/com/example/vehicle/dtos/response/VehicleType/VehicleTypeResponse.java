package com.example.vehicle.dtos.response.VehicleType;

import com.example.vehicle.dtos.response.Vehicle.VehicleResponse;
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

    List<VehicleResponse> vehicles;
}
