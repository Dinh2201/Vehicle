package com.example.vehicle.dtos.response.vehicle;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleLocationResponse {
     Long vehicleId;

     String vehicleName;

     String licensePlate;

     double latitude;

     double longitude;

     LocalDateTime updatedAt;

     long vehicleType;

     DriverDTO driver;


}
