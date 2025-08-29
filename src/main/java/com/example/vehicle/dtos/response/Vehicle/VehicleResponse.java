package com.example.vehicle.dtos.response.Vehicle;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleResponse {
    Long vehicleId;

    String vehicleName;

    String licensePlate;

    String status;

    LocalDate signupDate;

    Double latitude;

    Double longitude;

    long vehicleType; // chứa ID

     DriverDTO driver;

////     VehicleTypeDTO vehicleType;
//    List<Long> drivers;
}


