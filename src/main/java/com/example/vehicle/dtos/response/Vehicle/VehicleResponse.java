package com.example.vehicle.dtos.response.Vehicle;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

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

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    LocalDateTime signupDate;

    Double latitude;

    Double longitude;

    long vehicleType; // chứa ID

     DriverDTO driver;

////     VehicleTypeDTO vehicleType;
//    List<Long> drivers;
}


