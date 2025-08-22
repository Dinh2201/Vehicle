package com.example.vehicle.dtos.response.VehicleType;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleTypeResponse {
    Long vehicleTypeId;

    String name;

    String description;
}
