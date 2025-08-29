package com.example.vehicle.dtos.response.Vehicle;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleResponseNoVehicleType {

    Long vehicleId;

    String vehicleName;

    String licensePlate;

    String status;

    LocalDate signupDate;

    Double latitude;

    Double longitude;

    DriverDTO driver;
}
