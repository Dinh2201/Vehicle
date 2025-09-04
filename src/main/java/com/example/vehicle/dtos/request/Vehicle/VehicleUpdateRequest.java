package com.example.vehicle.dtos.request.Vehicle;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleUpdateRequest {

    String vehicleName;

    String licensePlate;

    String status;

    LocalDate signupDate;

    long vehicleType; // chứa ID

    Long driver;
}
