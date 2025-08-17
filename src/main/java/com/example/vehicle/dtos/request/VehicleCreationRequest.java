package com.example.vehicle.dtos.request;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VehicleCreationRequest {

     String vehicleName;

     String licensePlate;

     String status;

     LocalDate signupDate;

     String vehicleType; // chứa ID

     String driver;
}
