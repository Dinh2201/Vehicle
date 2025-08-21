package com.example.vehicle.dtos.request.VehicleType;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleTypeCreationRequest {
     String name;

     String description;
}
